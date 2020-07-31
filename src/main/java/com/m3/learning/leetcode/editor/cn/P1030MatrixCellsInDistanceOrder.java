//给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。 
//
// 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。 
//
// 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈
//顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。） 
//
// 
//
// 示例 1： 
//
// 输入：R = 1, C = 2, r0 = 0, c0 = 0
//输出：[[0,0],[0,1]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
// 
//
// 示例 2： 
//
// 输入：R = 2, C = 2, r0 = 0, c0 = 1
//输出：[[0,1],[0,0],[1,1],[1,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
//[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
// 
//
// 示例 3： 
//
// 输入：R = 2, C = 3, r0 = 1, c0 = 2
//输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
//解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
//其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= R <= 100 
// 1 <= C <= 100 
// 0 <= r0 < R 
// 0 <= c0 < C 
// 
// Related Topics 排序 
// 👍 35 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.*;

public class P1030MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        Solution solution = new P1030MatrixCellsInDistanceOrder().new Solution();
        Solution2 solution2 = new P1030MatrixCellsInDistanceOrder().new Solution2();

        int R = 1, C = 2, r0 = 0, c0 = 0;
        int[][] values = solution.allCellsDistOrder(R, C, r0, c0);
        int[][] predicts = new int[][]{{0,0},{0,1}};
        System.out.println(Arrays.deepEquals(values, predicts));
        values = solution2.allCellsDistOrder(R, C, r0, c0);
        System.out.println(Arrays.deepEquals(values, predicts));

        R = 2;
        C = 2;
        r0 = 0;
        c0 = 1;
        values = solution.allCellsDistOrder(R, C, r0, c0);
        predicts = new int[][]{{0,1},{0,0},{1,1},{1,0}};
        System.out.println(Arrays.deepEquals(values, predicts));
        values = solution2.allCellsDistOrder(R, C, r0, c0);
        System.out.println(Arrays.deepEquals(values, predicts));

        R = 2;
        C = 3;
        r0 = 1;
        c0 = 2;
        values = solution.allCellsDistOrder(R, C, r0, c0);
        predicts = new int[][]{{1,2},{0,2},{1,1},{0,1},{1,0},{0,0}};
        System.out.println(Arrays.deepEquals(values, predicts));
        values = solution2.allCellsDistOrder(R, C, r0, c0);
        System.out.println(Arrays.deepEquals(values, predicts));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            List<int[]> cells = new ArrayList<>();
            int len = R * C;
            for (int i = 0; i < len; i++) {
                cells.add(new int[]{i / C, i % C});
            }

            Collections.sort(cells, (p1, p2) -> {
                return getDistance(p1, r0, c0) - getDistance(p2, r0, c0);
            });

            int[][] ret = new int[len][2];
            for (int i = 0; i < len; i++) {
                ret[i] = cells.get(i);
            }
            return ret;
        }

        private int getDistance(int[] p, int r0, int c0) {
            return Math.abs(p[0] - r0) + Math.abs(p[1] - c0);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int[][] ret = new int[R*C][2];
            int dist = 0;
            int cnt = 0;
            int[] factor = {-1, 1};
            while(cnt < R*C) {
                for(int rowDist =0 ; rowDist <= dist; rowDist++) {
                    int colDist = dist-rowDist;
                    for(int i = 0; i < 2; i++) {
                        int row = r0 + factor[i]*rowDist;
                        for(int j = 0; j <2; j++) {
                            int col = c0 + factor[j]*colDist;
                            if(row >=0 && row < R && col >=0 && col < C) {
                                ret[cnt][0] = row;
                                ret[cnt][1] = col;
                                cnt++;
                            }
                            if(colDist == 0) break;
                        }
                        if(rowDist == 0) break;
                    }
                }
                dist++;
            }
            return ret;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)
}