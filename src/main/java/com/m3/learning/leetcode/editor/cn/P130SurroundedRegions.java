//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 362 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.*;

public class P130SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new P130SurroundedRegions().new Solution();
        char[][] board = {
                {'X','X', 'X', 'X', 'O'},
                {'X','O', 'O', 'X', 'O'},
                {'X','X', 'O', 'X', 'O'},
                {'X','O', 'X', 'X', 'O'}
        };
        solution.solve(board);
        for(char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        board = new char[][]{
                {'X','X', 'X', 'X'}
        };
        solution.solve(board);
        System.out.println(board);

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return;
            }
            int rows = board.length;
            int cols = board[0].length;

            Queue<Integer> queue = new LinkedList<>();
            for (int row = 0; row < rows; row++) {
                if (board[row][0] == 'O') {
                    queue.add(row * cols);
                }
                if (board[row][cols - 1] == 'O') {
                    queue.add(row * cols + cols - 1);
                }
            }
            for (int col = 1; col < cols - 1; col++) {
                if (board[0][col] == 'O') {
                    queue.add(col);
                }
                if (board[rows - 1][col] == 'O') {
                    queue.add((rows - 1) * cols + col);
                }
            }
            Set<Integer> opendOSets = new HashSet<>(queue);
            Set<Integer> visited = new HashSet<>(queue);
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                for (int i = 0; i < queueSize; i++) {
                    int loc = queue.poll();
                    visited.add(loc);
                    int row = loc / cols;
                    int col = loc % cols;
                    if (col < cols - 1 && board[row][col + 1] == 'O' && !visited.contains(loc + 1)) {
                        queue.add(loc + 1);
                        visited.add(loc + 1);
                        opendOSets.add(loc+1);
                    }
                    if (col > 1 && board[row][col - 1] == 'O' && !visited.contains(loc - 1)) {
                        queue.add(loc - 1);
                        visited.add(loc - 1);
                        opendOSets.add(loc-1);
                    }
                    if (row > 1 && board[row - 1][col] == 'O' && !visited.contains(loc - cols)) {
                        queue.add(loc - cols);
                        visited.add(loc - cols);
                        opendOSets.add(loc -cols);
                    }
                    if (row < rows - 1 && board[row + 1][col] == 'O' && !visited.contains(loc + cols)) {
                        queue.add(loc + cols);
                        visited.add(loc + cols);
                        opendOSets.add(loc+cols);
                    }

                }
            }
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (board[row][col] == 'O' && !opendOSets.contains(row * cols + col)) {
                        board[row][col] = 'X';
                    }
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}