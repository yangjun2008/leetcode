//「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。 
//
// 游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。 
//
// 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ： 
//
// 
// 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。 
// 地板用字符 '.' 表示，意味着可以自由行走。 
// 墙用字符 '#' 表示，意味着障碍物，不能通行。 
// 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。 
// 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。 
// 玩家无法越过箱子。 
// 
//
// 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#",".","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：3
//解释：我们只需要返回推箱子的次数。 
//
// 示例 2： 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#","#","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：-1
// 
//
// 示例 3： 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T",".",".","#","#"],
//             ["#",".","#","B",".","#"],
//             ["#",".",".",".",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：5
//解释：向下、向左、向左、向上再向上。
// 
//
// 示例 4： 
//
// 输入：grid = [["#","#","#","#","#","#","#"],
//             ["#","S","#",".","B","T","#"],
//             ["#","#","#","#","#","#","#"]]
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 20 
// 1 <= grid[i].length <= 20 
// grid 仅包含字符 '.', '#', 'S' , 'T', 以及 'B'。 
// grid 中 'S', 'B' 和 'T' 各只能出现一个。 
// 
// Related Topics 广度优先搜索 
// 👍 44 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P1263MinimumMovesToMoveABoxToTheirTargetLocation {
    public static void main(String[] args) {
        Solution solution = new P1263MinimumMovesToMoveABoxToTheirTargetLocation().new Solution();
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '.', '.', '#', '#'},
                {'#', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}};
        //(char[][] grid, int[] player, int[] target, int[] oldbox, int[] newbox)
        //System.out.println(solution.canPlayerReatched(grid, new int[]{4,4}, new int[]{2,3}, new int[]{2, 3}, new int[]{2,3}));
        System.out.println(solution.minPushBox(grid));
        grid = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', '.', 'B', 'T', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}};
        //(char[][] grid, int[] player, int[] target, int[] oldbox, int[] newbox)
        //System.out.println(solution.canPlayerReatched(grid, new int[]{4,4}, new int[]{2,3}, new int[]{2, 3}, new int[]{2,3}));
        System.out.println(solution.minPushBox(grid));

        grid = new char[][]{
                {'#', '.', '.', '#', '#', '#', '#', '#'},
                {'#', '.', '.', 'T', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '#', '.', 'S', '#'},
                {'#', '.', '.', '#', '#', '#', '#', '#'}
        };
        System.out.println(solution.minPushBox(grid));

        grid = new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}};
        //(char[][] grid, int[] player, int[] target, int[] oldbox, int[] newbox)
        //System.out.println(solution.canPlayerReatched(grid, new int[]{4,4}, new int[]{2,3}, new int[]{2, 3}, new int[]{2,3}));
        System.out.println(solution.minPushBox(grid));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPushBox(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] boxStart = new int[2];
        int[] target = new int[2];
        int[] player = new int[2];
        makeBTS(grid, boxStart, target, player);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(boxStart);
        Queue<int[]> playerQueue = new LinkedList<>();
        playerQueue.add(player);
        boolean[][][][] visited = new boolean[rows][cols][3][3];
        for(boolean[][][] onerow : visited) {
            for(boolean[][] onecell : onerow) {
                for(boolean[] onedir: onecell) {
                    Arrays.fill(onedir, false);
                }
            }
        }
        for(boolean[] onedir : visited[boxStart[0]][boxStart[1]]) {
            Arrays.fill(onedir, true);
        }
        int[][] dirs = new int[][]{{1, 0}, {0,1}, {-1,0}, {0, -1}};
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int[] curPlayer = playerQueue.poll();
                for (int[] dir : dirs) {
                    int row = pos[0] + dir[0];
                    int col = pos[1] + dir[1];
                    int nrow = pos[0] - dir[0];
                    int ncol = pos[1] - dir[1];

                    if (checkLocValid(grid, row, col) && checkLocValid(grid, nrow, ncol)) {
                            int[] tpos = new int[]{row, col};
                            int[] nPlayer = new int[]{nrow, ncol};
                            if (visited[row][col][dir[0] + 1][dir[1] + 1] == false) {
                                if (canPlayerReatched(grid, curPlayer, nPlayer, boxStart, pos)) {
                                    if(row == target[0] && col == target[1]) {
                                        return count+1;
                                    }
                                    queue.add(tpos);
                                    playerQueue.add(pos);
                                    visited[row][col][dir[0] + 1][dir[1] + 1] = true;
                                }
                            }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private boolean checkLocValid(char[][] grid, int row, int col) {
        return row>=0 && row<grid.length && col>=0 && col<grid[0].length && grid[row][col]!='#';
    }

    private boolean canPlayerReatched(char[][] grid, int[] player, int[] target, int[] oldbox, int[] newbox) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(player);
        boolean[][] visited = new boolean[rows][cols];
        for(boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        grid[oldbox[0]][oldbox[1]] = '.';
        grid[newbox[0]][newbox[1]] = 'B';
        int[][] dirs = new int[][]{{-1, 0}, {1,0}, {0,-1}, {0,1}};
        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            if(pos[0]== target[0] & pos[1] == target[1]) {
                grid[oldbox[0]][oldbox[1]] = 'B';
                grid[newbox[0]][newbox[1]] = '.';
                return true;
            }
            for(int[] dir : dirs) {
                int row = pos[0]+dir[0];
                int col = pos[1]+dir[1];
                if(row>=0 && row<rows && col>=0 && col<cols) {
                    if (visited[row][col] == false && (grid[row][col] != '#' && grid[row][col]!='B' )) {
                        visited[row][col] = true;
                        queue.add(new int[]{row, col});
                    }
                }
            }
        }
        grid[oldbox[0]][oldbox[1]] = 'B';
        grid[newbox[0]][newbox[1]] = '.';
        return false;
    }

    private void makeBTS(char[][] grid,int[] boxStart, int[] target, int[] player) {
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 'B') {
                    boxStart[0] = i;
                    boxStart[1] = j;
                }
                else if(grid[i][j] == 'T') {
                    target[0] = i;
                    target[1] = j;
                }
                else if(grid[i][j] == 'S') {
                    player[0]= i;
                    player[1] = j;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}