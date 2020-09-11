//由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。 
//
// 给定球的起始位置，目的地和迷宫，找出让球停在目的地的最短距离。距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。如果球无法停在目的地，返回
// -1。 
//
// 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。 
//
// 
//
// 示例 1: 
//
// 输入 1: 迷宫由以下二维数组表示
//
//0 0 1 0 0
//0 0 0 0 0
//0 0 0 1 0
//1 1 0 1 1
//0 0 0 0 0
//
//输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
//输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
//
//输出: 12
//
//解析: 一条最短路径 : left -> down -> left -> down -> right -> down -> right。
//             总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。
//
// 
//
// 示例 2: 
//
// 输入 1: 迷宫由以下二维数组表示
//
//0 0 1 0 0
//0 0 0 0 0
//0 0 0 1 0
//1 1 0 1 1
//0 0 0 0 0
//
//输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
//输入 3: 目的地坐标 (rowDest, colDest) = (3, 2)
//
//输出: -1
//
//解析: 没有能够使球停在目的地的路径。
//
// 
//
// 
//
// 注意: 
//
// 
// 迷宫中只有一个球和一个目的地。 
// 球和目的地都在空地上，且初始时它们不在同一位置。 
// 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。 
// 迷宫至少包括2块空地，行数和列数均不超过100。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 39 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.*;

public class P505TheMazeIi {
    public static void main(String[] args) {
        Solution solution = new P505TheMazeIi().new Solution();
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        System.out.println(solution.shortestDistance(maze, new int[]{0,4}, new int[]{3,2})); //-1
        System.out.println(solution.shortestDistance(maze, new int[]{0,4}, new int[]{4,4})); //12

        maze = new int[][]{
                {0,0,0,0,1,0,0},
                {0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1},
                {0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,1,0,0,0,1},
                {0,0,0,0,1,0,0}};
        System.out.println(solution.shortestDistance(maze, new int[]{0,0}, new int[]{8,6})); //26

        maze = new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        System.out.println(solution.shortestDistance(maze, new int[]{1,1}, new int[]{1,2})); //3
    }

    /*
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private static final int DIR_NONE = -1;
        private static final int DIR_UP = 0;
        private static final int DIR_DOWN = 1;
        private static final int DIR_RIGHT = 2;
        private static final int DIR_LEFT = 3;

        private int curDir = DIR_NONE;
        private int rows;
        private int cols;
        int[][] fullmaze;
        private Set<String> visted;
        private Queue<Integer> dirQueue;

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            rows = maze.length;
            cols = maze[0].length;
            fullmaze = new int[rows+2][cols+2];
            Arrays.fill(fullmaze[0], 1);
            Arrays.fill(fullmaze[rows+1], 1);
            for(int i = 1; i < rows+1; i++) {
                fullmaze[i][0] = 1;
                fullmaze[i][cols+1]=1;
            }
            for(int i = 0; i < rows; i++) {
                System.arraycopy(maze[i], 0, fullmaze[i+1], 1, cols);
            }
            rows = rows+2;
            cols = cols+2;

            int startPos = (start[0]+1)*cols+start[1]+1;
            int targetPos = (destination[0]+1)*cols+destination[1]+1;
            if(startPos == targetPos) {
                return getNextAvaiablePos(startPos).length == 4 ? -1:0;
            }
            //System.out.println(startPos);
            //System.out.println(targetPos);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startPos);
            visted = new HashSet<>();
            visted.add(getKey(startPos, DIR_NONE));
            dirQueue = new LinkedList<>();
            dirQueue.add(DIR_NONE);
            int step = 0;
            boolean isTargetWall = fullmaze[targetPos/cols][targetPos%cols] == 1;
            while(!queue.isEmpty()) {
                int curSize = queue.size();
                for(int i = 0; i < curSize; i++) {
                    int curPos = queue.poll();
                    curDir = dirQueue.poll();
                    if(curDir == DIR_NONE) {
                        int[] poses = getNextAvaiablePos(curPos);
                        if(poses.length<4&&isTargetWall) {
                            if(targetPos== curPos -cols || targetPos == curPos+cols || targetPos == curPos+1 || targetPos == curPos-1) {
                                return step+1;
                            }
                        }
                        for (int pos : poses) {
                            queue.add(pos);
                            //visted.add(getKey(pos,curDir));
                        }
                    }
                    else {
                        int nextPos = next(curPos, targetPos);
                        if(nextPos == -2) {
                            continue;
                        }
                        else if(nextPos == -1) {
                            if(curPos == targetPos) {
                                return step;
                            }
                            curDir = DIR_NONE;
                            int[] poses = getNextAvaiablePos(curPos);
                            for (int pos : poses) {
                                queue.add(pos);
                                //visted.add(getKey(pos,curDir));
                            }
                        }
                        else {
                             queue.add(nextPos);
                             dirQueue.add(curDir);
                             visted.add(getKey(nextPos,curDir));
                        }
                    }
                }
                step++;
            }
            return -1;
        }

        private String getKey(int pos, int dir) {
            return pos+"."+dir;
        }

        private int next(int pos, int targetPos) {
            int npos = pos;
            if(curDir == DIR_UP) {
                npos = pos - cols;
            }
            else if(curDir == DIR_DOWN) {
                npos = pos+cols;
            }
            else if(curDir == DIR_LEFT) {
                npos = pos - 1;
            }
            else {
                npos = pos + 1;
            }
            if(visted.contains(npos)) {
                return -2;
            }
            if(fullmaze[npos/cols][npos%cols] == 0) {
                return npos;
            }
            else {
                return -1;
            }
        }

        private int[] getNextAvaiablePos(int pos) {
            List<Integer> list = new ArrayList<>();
            //UP
            int upNext = pos - cols;
            makeAvaiableList(list, upNext, DIR_UP);
            //DOWN
            upNext = pos + cols;
            makeAvaiableList(list, upNext, DIR_DOWN);
            //LEFT
            upNext = pos - 1;
            makeAvaiableList(list, upNext, DIR_LEFT);
            //RIGHT
            upNext = pos + 1;
            makeAvaiableList(list, upNext, DIR_RIGHT);
            return list.stream().mapToInt(Integer::intValue).toArray();
        }

        private void makeAvaiableList(List<Integer> list, int pos, int dir) {
            if(!visted.contains(getKey(pos,dir)) && fullmaze[pos/cols][pos%cols] == 0) {
                list.add(pos);
                dirQueue.add(dir);
                visted.add(getKey(pos,dir));
            }
        }
}
//leetcode submit region end(Prohibit modification and deletion)


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int[][] dirs = {
                {0,1}, //RIGHT
                {0,-1},//LEFT
                {1,0}, //DOWN
                {-1,0} //UP
        };

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int rows = maze.length;
            int cols = maze[0].length;
            int[][] distance = new int[rows][cols];
            for(int[] row : distance) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            distance[start[0]][start[1]] = 0;
            dfs(maze, start, distance);
            int ret = distance[destination[0]][destination[1]];
            return ret == Integer.MAX_VALUE ? -1: ret;
        }

        private void dfs(int[][] maze, int[] start, int[][] distance) {
            for(int[] dir : dirs) {
                int row = start[0] + dir[0];
                int col = start[1] + dir[1];
                int count = 0;
                while(row>=0 && row < maze.length && col>=0 && col <maze[0].length && maze[row][col] == 0) {
                    row += dir[0];
                    col += dir[1];
                    count++;
                }
                try {
                    if (distance[start[0]][start[1]] + count < distance[row - dir[0]][col - dir[1]]) {
                        distance[row - dir[0]][col - dir[1]] = distance[start[0]][start[1]] + count;
                        dfs(maze, new int[]{row - dir[0], col - dir[1]}, distance);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    */

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int[][] dirs = {
                {0, 1}, //RIGHT
                {0, -1},//LEFT
                {1, 0}, //DOWN
                {-1, 0} //UP
        };

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int rows = maze.length;
            int cols = maze[0].length;
            int[][] distance = new int[rows][cols];
            for (int[] row : distance) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            distance[start[0]][start[1]] = 0;
            bfs(maze, start, distance);
            int ret = distance[destination[0]][destination[1]];
            return ret == Integer.MAX_VALUE ? -1 : ret;
        }

        private void bfs(int[][] maze, int[] start, int[][] distance) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(start);
            while(!queue.isEmpty()) {
                int[] s = queue.poll();
                for(int[] dir: dirs) {
                    int row = s[0] + dir[0];
                    int col = s[1] + dir[1];
                    int count = 0;
                    while(row>=0 && row < maze.length && col>=0 && col <maze[0].length && maze[row][col] == 0) {
                        row += dir[0];
                        col += dir[1];
                        count++;
                    }
                    if (distance[s[0]][s[1]] + count < distance[row - dir[0]][col - dir[1]]) {
                        distance[row - dir[0]][col - dir[1]] = distance[s[0]][s[1]] + count;
                        queue.add(new int[]{row-dir[0], col-dir[1]});
                    }
                }
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)
}