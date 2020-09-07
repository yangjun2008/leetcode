//你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够造在一个距离周边设施都比较方便的地方，通过调研，你希望从它出发能在 最短的距离和 内抵达周
//边全部的建筑物。请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。 
//
// 
//
// 提示： 
//
// 你只能通过向上、下、左、右四个方向上移动。 
//
// 给你一个由 0、1 和 2 组成的二维网格，其中： 
//
// 
// 0 代表你可以自由通过和选择建造的空地 
// 1 代表你无法通行的建筑物 
// 2 代表你无法通行的障碍物 
// 
//
// 
//
// 示例： 
//
// 输入：[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
//1 - 0 - 2 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//输出：7 
//解析：
//给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
//由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
// 
//
// 
//
// 注意： 
//
// 
// 题目数据保证至少存在一栋建筑物，如果无法按照上述规则返回建房地点，则请你返回 -1。 
// 
// Related Topics 广度优先搜索 
// 👍 41 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.*;

public class P317ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        Solution solution = new P317ShortestDistanceFromAllBuildings().new Solution();
        int[][] grid = new int[][] {
                {1,1,1,1,1,0},
                {0,0,0,0,0,1},
                {0,1,1,0,0,1},
                {1,0,0,1,0,1},
                {1,0,1,0,0,1},
                {1,0,0,0,0,1},
                {0,1,1,1,1,0}
        };
        System.out.println(solution.shortestDistance(grid));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int buildingCount = 0;
        for(int i = 0; i < rows; i++) {
            for(int j =0;j < cols; j++) {
                if(grid[i][j] == 0) {
                    queue.add(i*cols+j);
                }
                else if(grid[i][j] == 1) {
                    buildingCount++;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        int size = queue.size();
        for(int i = 0; i < size; i++) {
            int loc = queue.poll();
            int dis = getMinDistance(grid, rows, cols, loc, buildingCount);
            System.out.println(loc + ", " + dis);
            minDistance = Math.min(minDistance, dis);
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private int getMinDistance(int[][] grid, int rows, int cols, int targetLoc, int buildingCount) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(targetLoc);
        visited.add(targetLoc);
        int step = 0;
        int turn = 0;
        int counts = buildingCount;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size; i++) {
                int loc = queue.poll();
                int y =  loc / cols;
                int x = loc % cols;
                if(y<rows-1 && !visited.contains(loc+cols)) {
                    if(grid[y+1][x] == 0) {
                        queue.add(loc+cols);
                    }
                    else if(grid[y+1][x] == 1) {
                        step = step + turn+1;
                        counts--;
                    }
                    visited.add(loc+cols);
                }
                if(y>0 && !visited.contains(loc-cols)) {
                    if(grid[y-1][x] == 0) {
                        queue.add(loc-cols);
                    }
                    else if(grid[y-1][x] == 1) {
                        step = step + turn+1;
                        counts--;
                    }
                    visited.add(loc-cols);
                }
                if(x>0 && !visited.contains(loc-1)) {
                    if(grid[y][x-1] == 0) {
                        queue.add(loc-1);
                    }
                    else if(grid[y][x-1] == 1) {
                        step = step + turn+1;
                        counts--;
                    }
                    visited.add(loc-1);
                }
                if(x<cols-1 && !visited.contains(loc+1)) {
                    if(grid[y][x+1] == 0) {
                        queue.add(loc+1);
                    }
                    else if(grid[y][x+1] == 1) {
                        step = step + turn+1;
                        counts--;
                    }
                    visited.add(loc+1);
                }
            }
            if(counts == 0) {
                return step;
            }
            turn++;
        }
        return Integer.MAX_VALUE;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}