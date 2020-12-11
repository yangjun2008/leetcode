//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针 
// 👍 1611 👎 0


package com.m3.learning.jiuyang.dp;

import java.util.Stack;

public class P42TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(solution.trap(new int[]{3,0,2}));
        System.out.println(solution.trap(new int[]{2,1,0,2}));
        System.out.println(solution.trap(new int[]{5,4,1,2}));
        System.out.println(solution.trap(new int[]{5,2,1,2,1,5}));
        System.out.println(solution.trap(new int[]{0,7,1,4,6}));
        System.out.println(solution.trap(new int[]{9,6,8,8,5,6,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            if(height == null||height.length<3) {
                return 0;
            }
            int sum = 0;
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < height.length; i++) {
                while(!stack.empty() && height[i]>height[stack.peek()]) {
                    int top = stack.pop();
                    if(stack.empty()) {
                        break;
                    }
                    int distance = i - stack.peek() - 1;
                    int min = Math.min(height[stack.peek()], height[i]);
                    sum += distance * (min - height[top]);
                }
                stack.push(i);
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 解法二: 动态规划
     * 我们注意到，解法二中。对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。
     *
     * 首先用两个数组，max_left [i] 代表第 i 列左边最高的墙的高度，max_right[i] 代表第 i 列右边最高的墙的高度。（一定要注意下，第 i 列左（右）边最高的墙，是不包括自身的，和 leetcode 上边的讲的有些不同）
     *
     * 对于 max_left我们其实可以这样求。
     *
     * max_left [i] = Max(max_left [i-1],height[i-1])。它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。
     *
     * 对于 max_right我们可以这样求。
     *
     * max_right[i] = Max(max_right[i+1],height[i+1]) 。它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了。
     */
    class Solution2 {
        public int trap(int[] height) {
            int sum = 0;
            int[] max_left = new int[height.length];
            int[] max_right = new int[height.length];

            for (int i = 1; i < height.length - 1; i++) {
                max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
            }
            for (int i = height.length - 2; i >= 0; i--) {
                max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
            }
            for (int i = 1; i < height.length - 1; i++) {
                int min = Math.min(max_left[i], max_right[i]);
                if (min > height[i]) {
                    sum = sum + (min - height[i]);
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

//leetcode submit region begin(Prohibit modification and deletion)
     /*
     * 解法三: 动态规划+双指针
     */
    class Solution3 {
        public int trap(int[] height) {
            int sum = 0;
            int[] max_right = new int[height.length];

            for (int i = height.length - 2; i >= 0; i--) {
                max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
            }
            int max_left = 0;
            for (int i = 1; i < height.length - 1; i++) {
                max_left= Math.max(max_left, height[i - 1]);
                int min = Math.min(max_left, max_right[i]);
                if (min > height[i]) {
                    sum = sum + (min - height[i]);
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}