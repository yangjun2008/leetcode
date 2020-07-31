//用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。 
//
// 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。 
//
// 以下是井字游戏的规则： 
//
// 
// 玩家轮流将字符放入空位（" "）中。 
// 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。 
// “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。 
// 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。 
// 当所有位置非空时，也算为游戏结束。 
// 如果游戏结束，玩家不允许再放置字符。 
// 
//
// 
//示例 1:
//输入: board = ["O  ", "   ", "   "]
//输出: false
//解释: 第一个玩家总是放置“X”。
//
//示例 2:
//输入: board = ["XOX", " X ", "   "]
//输出: false
//解释: 玩家应该是轮流放置的。
//
//示例 3:
//输入: board = ["XXX", "   ", "OOO"]
//输出: false
//
//示例 4:
//输入: board = ["XOX", "O O", "XOX"]
//输出: true
// 
//
// 说明: 
//
// 
// 游戏板 board 是长度为 3 的字符串数组，其中每个字符串 board[i] 的长度为 3。 
// board[i][j] 是集合 {" ", "X", "O"} 中的一个字符。 
// 
// Related Topics 递归 数学 
// 👍 21 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P794ValidTicTacToeState {
    public static void main(String[] args) {
        Solution solution = new P794ValidTicTacToeState().new Solution();
        String[] input = new String[]{"O  ", "   ", "   "};
        System.out.println(solution.validTicTacToe(input) == false);

        input = new String[]{"XOX", " X ", "   "};
        System.out.println(solution.validTicTacToe(input) == false);

        input = new String[]{"XXX", "   ", "OOO"};
        System.out.println(solution.validTicTacToe(input) == false);

        input = new String[]{"XOX", "O O", "XOX"};
        System.out.println(solution.validTicTacToe(input) == true);

        input = new String[]{"XXX", "OOX", "OOX"};
        System.out.println(solution.validTicTacToe(input) == true);

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 规则归纳：具体摆放位置无关化，抽取统计规律
         * 1. 因为第一个玩家总是X，并且是轮流放置，所以X数量一定等于O或比O+1
         * 2. 不可能同时出现两行X或两行O
         * 3. 不可能同时出现两列X或两列O
         * 4. 不需要考虑对角线情况，因为一方有对角线，另一方绝对不可能出现3个串联的X或O
         * @param board
         * @return
         */
        public boolean validTicTacToe(String[] board) {
            int xCount = 0;
            int yCount = 0;
            for(String s : board) {
                for(char c : s.toCharArray()) {
                    if(c == 'X') {
                        xCount++;
                    }
                    else if(c == 'O') {
                        yCount++;
                    }
                }
            }
            if(xCount == yCount) {
                //x 不能出现3连
                return !isWin(board, 'X');
            }
            else if(xCount == yCount+1) {
                //o不能出现3连
                return !isWin(board, 'O');
            }
            else {
                return false;
            }
        }

        private boolean isWin(String[] board, char p) {
            for(int i = 0; i < 3 ; i++) {
                if(board[i].charAt(0) == p && board[i].charAt(1) == p && board[i].charAt(2) == p) {
                    return true;
                }
                if(board[0].charAt(i) == p && board[1].charAt(i) == p && board[2].charAt(i) == p) {
                    return true;
                }
            }
            if(board[0].charAt(0) == p && board[1].charAt(1) == p && board[2].charAt(2) == p) {
                return true;
            }
            if(board[2].charAt(0) == p && board[1].charAt(1) == p && board[0].charAt(2) == p) {
                return true;
            }
            return false;
        }

}
//leetcode submit region end(Prohibit modification and deletion)

}