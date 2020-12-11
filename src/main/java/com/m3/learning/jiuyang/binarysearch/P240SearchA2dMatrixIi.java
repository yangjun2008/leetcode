//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 二分查找 分治算法 
// 👍 492 👎 0


package com.m3.learning.jiuyang.binarysearch;

public class P240SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new P240SearchA2dMatrixIi().new Solution();
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(solution.searchMatrix(matrix, 5));

        matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(solution.searchMatrix(matrix, 20));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int shorterDim = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < shorterDim; i++) {
                boolean verticalFound = binarySearch(matrix, target, i, true);
                boolean horizontalFound = binarySearch(matrix, target, i, false);
                if (verticalFound || horizontalFound) {
                    return true;
                }
            }
            return false;
        }

        private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;

            while (hi >= lo) {
                int mid = (lo + hi) / 2;
                if (vertical) {
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else {
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}