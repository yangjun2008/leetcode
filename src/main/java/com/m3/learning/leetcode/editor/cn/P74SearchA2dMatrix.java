//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找 
// 👍 215 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P74SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new P74SearchA2dMatrix().new Solution();
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(solution.searchMatrix(matrix, 50));
        matrix = new int[][]{
                {1}
        };
        System.out.println(solution.searchMatrix(matrix, 1));
        matrix = new int[][]{
                {1},
                {2}
        };
        System.out.println(solution.searchMatrix(matrix, 2));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if(matrix == null || matrix.length == 0) {
                return false;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            if(row == 1 && col == 1) {
                return matrix[0][0] == target;
            }
            int length = row*col;
            int start = 0, end = length-1;
            int mid;
            while(start < end) {
                if(target < getValue(start, matrix, col) || target > getValue(end, matrix, col)) {
                    return false;
                }
                else if(target == getValue(start, matrix, col) || target == getValue(end, matrix, col)) {
                    return true;
                }
                else if(start == end -1) {
                    return false;
                }
                mid = start + (end-start)/2;
                int midValue = getValue(mid, matrix,col);
                if(target < midValue) {
                    end = mid;
                }
                else if(target > midValue) {
                    start = mid;
                }
                else {
                    return true;
                }
            }
            return false;
        }

        private int getValue(int loc, int[][]matrix, int col) {
            return matrix[loc/col][loc%col];
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}