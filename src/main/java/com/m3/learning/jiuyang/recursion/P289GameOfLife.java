//根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。 
//
// 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dea
//d）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律： 
//
// 
// 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 
// 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活； 
// 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 
// 如果死细胞周围正好有三个活细胞，则该位置死细胞复活； 
// 
//
// 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生
//和死亡是同时发生的。 
//
// 
//
// 示例： 
//
// 输入： 
//[
//  [0,1,0],
//  [0,0,1],
//  [1,1,1],
//  [0,0,0]
//]
//输出：
//[
//  [0,0,0],
//  [1,0,1],
//  [0,1,1],
//  [0,1,0]
//] 
//
// 
//
// 进阶： 
//
// 
// 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。 
// 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？ 
// 
// Related Topics 数组 
// 👍 294 👎 0


package com.m3.learning.jiuyang.recursion;

public class P289GameOfLife {
    public static void main(String[] args) {
        Solution solution = new P289GameOfLife().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void gameOfLife(int[][] board) {
        int[][] offset = {{-1,-1}, {-1, 0}, {-1,1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int[] idx = new int[2];
        int[][] nboard = new int[board.length][board[0].length];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                nboard[row][col] = board[row][col];
                int liveCount = 0;
                for(int i = 0; i < offset.length; i++) {
                    idx[0] = row+offset[i][0];
                    idx[1] = col+offset[i][1];
                    if(isValid(idx, board.length, board[0].length)) {

                        if(board[idx[0]][idx[1]]==1)
                            liveCount++;
                    }
                }
                System.out.println(liveCount);
                if(board[row][col] == 1 && (liveCount<2 || liveCount>3)) {
                    nboard[row][col] = 0;
                }
                else if(board[row][col] == 0 && liveCount==3) {
                    nboard[row][col] = 1;
                }
            }
        }
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                board[row][col] = nboard[row][col];
            }
        }
    }

    private boolean isValid(int[] idx,int ROW, int COL) {
        return idx[0]>=0 && idx[0]<COL && idx[1]>=0 && idx[1] < ROW;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}