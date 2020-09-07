//给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 示例: 
//
// 输入:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//输出: 6 
// Related Topics 栈 数组 哈希表 动态规划 
// 👍 570 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Stack;

public class P85MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new P85MaximalRectangle().new Solution();
        char[][] input = new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(solution.maximalRectangle(input));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            int max = 0;
            int[] counts = new int[col];
            for(int i = 0; i < row; i++) {
                for(int j = 0; j <col; j++) {
                    if(matrix[i][j] == '1') {
                        counts[j]++; //技巧，利用上次统计结果
                    }
                    else {
                        counts[j] = 0;
                    }
                }
                max = Math.max(max, largestRectangleArea(counts));

            }
            return max;
        }


        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack();
            stack.push(-1);
            int maxArea = Integer.MIN_VALUE;
            for(int i = 0; i < heights.length; i++) {
                while(stack.peek()!= -1 && heights[i] < heights[stack.peek()]) {
                    int top = stack.pop();
                    int width = i - stack.peek() -1;
                    maxArea = Math.max(maxArea, heights[top]*width);
                }
                stack.push(i);
            }
            while(stack.peek()!=-1) {
                int area = heights[stack.pop()]*(heights.length-stack.peek()-1);
                maxArea = Math.max(maxArea, area);
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}