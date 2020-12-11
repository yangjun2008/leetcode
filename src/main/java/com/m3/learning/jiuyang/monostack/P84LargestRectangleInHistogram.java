//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 876 👎 0


package com.m3.learning.jiuyang.monostack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P84LargestRectangleInHistogram {
    public static void main(String[] args) {
        /*Solution solution = new P84LargestRectangleInHistogram().new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(solution.largestRectangleArea(new int[]{6,7,5,2,4,5,9,3}));

        Solution2 solution2 = new P84LargestRectangleInHistogram().new Solution2();
        System.out.println(solution2.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(solution2.largestRectangleArea(new int[]{6,7,5,2,4,5,9,3}));*/

        Solution3 solution3 = new P84LargestRectangleInHistogram().new Solution3();
        System.out.println(solution3.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(solution3.largestRectangleArea(new int[]{6,7,5,2,4,5,9,3}));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            if(heights == null || heights.length == 0) {
                return 0;
            }
            int maxArea = Integer.MIN_VALUE;
            for(int i = 0; i < heights.length; i++) {
                int start = backwardLesser(heights, i);
                int end = forwardLesser(heights, i);
                maxArea = Math.max(heights[i]*(end-start+1), maxArea);
            }
            return maxArea;
        }

        private int backwardLesser(int[] array, int index) {
            for(int start=index; start>=0; start--) {
                if(array[start] < array[index]) {
                    return start+1;
                }
            }
            return 0;
        }

        private int forwardLesser(int[] array, int index) {
            for(int end = index; end < array.length; end++) {
                if(array[end] < array[index]) {
                    return end-1;
                }
            }
            return array.length-1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 单调栈
     */
    class Solution2 {
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

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 单调栈+哨兵
     */
    class Solution3 {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0) {
                return 0;
            }
            if (len == 1) {
                return heights[0];
            }

            int res = 0;

            int[] newHeights = new int[len + 2];
            newHeights[0] = 0;
            System.arraycopy(heights, 0, newHeights, 1, len);
            newHeights[len + 1] = 0;
            len += 2;
            heights = newHeights;

            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);

            for (int i = 1; i < len; i++) {
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    res = Math.max(res, curHeight * curWidth);
                }
                stack.addLast(i);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}