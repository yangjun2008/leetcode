package com.aiplayer.learning.leetcode.sn53;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //return solution_1(nums);
        return solution_2(nums);
    }

    private int solution_1(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]);
            result = Math.max(dp[i + 1], result);
        }
        return result;
    }

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
