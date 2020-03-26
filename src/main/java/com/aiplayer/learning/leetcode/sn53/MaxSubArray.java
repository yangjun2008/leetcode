package com.aiplayer.learning.leetcode.sn53;

/**
 * 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 * 考点：数组，动态规划
 * 难度：简单
 * 基础知识点：Math.max函数比较两数大小
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //return solution_1(nums);
        return solution_2(nums);
    }

    /**
     * 本题最为经典和广泛的解法是应用动态规划的思想来解答，其时间复杂度为O(n)。
     * 从数组第一个元素开始遍历，用一个一维数组存储遍历到当前元素的最大连续子数组的和。
     * 当遍历到第i个元素时，如果前i-1和元素中连续子数组和加上第i个元素时比第i个元素的值要大，那么就更新dp[i] = dp[i-1] + nums[i]，否则dp[i] = nums[i]。
     * 1ms,     97.6%
     * 41.8M    6.54%
     * @param nums
     * @return
     */
    private int solution_1(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]);
            result = Math.max(dp[i + 1], result);
        }
        return result;
    }

    /**
     *
     *  0ms,    100%
     * 41.5M   7.86%
     * @param nums
     * @return
     */
    private int solution_2(int[] nums) {
        int result = nums[0], cur = 0, last;
        for (int i = 0; i < nums.length; i++) {
            last = cur;
            cur = Math.max(last + nums[i], nums[i]);
            result = Math.max(cur, result);
        }
        return result;
    }
}
