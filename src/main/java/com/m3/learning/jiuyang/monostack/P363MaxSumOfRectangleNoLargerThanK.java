//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。 
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 
// 👍 119 👎 0


package com.m3.learning.jiuyang.monostack;

public class P363MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        Solution solution = new P363MaxSumOfRectangleNoLargerThanK().new Solution();
        int[][] inputs = new int[][]{
                {1, 0 , 1},
                {0, -2 ,3},
                {0, 1 ,3},
        };
        System.out.println(solution.maxSumSubmatrix(inputs, 5));
        inputs = new int[][]{
                {2, 2 , -1}
        };
        System.out.println(solution.maxSumSubmatrix(inputs, 4));

        inputs = new int[][]{
                {5, -4 , -3, 4},
                {-3, -4 , 4, 5},
                {5, 1 , 5, 4}
        };
        System.out.println(solution.maxSumSubmatrix(inputs, -2));
    }

    /**
     * 暴力求解
     */
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                for(int m = i; m < row; m++){
                    for(int n = j; n < col; n++) {
                        int sum = calcRetancle(matrix,i, j, m, n);
                        if(sum <= k) {
                            max = Math.max(max, sum);
                        }
                    }
                }
            }
        }
        return max;
    }

    private int calcRetancle(int[][] matrix, int fromRow, int fromCol, int toRow, int toCol) {
        int val = 0;
        for(int i =0; i < toRow-fromRow+1; i++) {
            for(int j = 0; j < toCol-fromCol+1; j++) {
                val += matrix[fromRow+i][fromCol+j];
            }
        }
        return val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 左右边界+暴力搜索
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            int max = Integer.MIN_VALUE;
            for(int l = 0; l < col; l++) {//枚举左边界
                int[] rowSum = new int[row];
                for(int r = l; r < col; r++) { //枚举右边界
                    for(int i = 0; i < row; i++){
                        rowSum[i]+=matrix[i][r]; //技巧：动态规划累加
                    }
                    max = Math.max(max, dpmax(rowSum, k));
                }
            }
            return max;
        }

        private int dpmax(int[] rowSum, int k) {
            if(rowSum.length == 0) {
                return 0;
            }
            int max = Integer.MIN_VALUE;
            for(int i = 0; i <rowSum.length; i++) {
               int count = 0;
                for(int j = i; j < rowSum.length; j++) {
                   count+=rowSum[j];
                   if(count <= k)  {
                        max = Math.max(max, count);
                   }
               }

            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 动态规划
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution3 {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int rows = matrix.length;
            int cols = matrix[0].length;
            int max = Integer.MIN_VALUE;
            //int[][][][] dp = new int[rows + 1][cols + 1][rows + 1][cols + 1]; // from (i1,j1) to (i2,j2)
            for (int i1 = 1; i1 <= rows; i1++) {
                for (int j1 = 1; j1 <= cols; j1++) {
                    int[][] dp = new int[rows+1][cols+1];
                    dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                    for (int i2 = i1; i2 <= rows; i2++) {
                        for (int j2 = j1; j2 <= cols; j2++) {
                            dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                            if (dp[i2][j2] <= k && dp[i2][j2] > max) max = dp[i2][j2];
                        }
                    }
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}