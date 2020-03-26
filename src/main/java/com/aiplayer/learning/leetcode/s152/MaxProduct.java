package com.aiplayer.learning.leetcode.s152;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 */
public class MaxProduct {

    /**
     * 这题其实是最大子序和一个变例，由加法变换成了乘法操作（依旧是应用动态规划的思路）。
     * 此时需要做的改变是定义两个变量来存储当前子序列的乘积，一个是保存最大值，一个是保存最小值（包含负数的子序列）。
     * 3ms      43.12%
     * 38.7M    7.86%
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        return solution_1(nums);
    }

    private int solution_1(int[] nums) {
        int result = nums[0], n_max = 1, n_min = 1;
        for (int n : nums) {
            if (n < 0) {
                int temp = n_max;
                n_max = Math.max(n_min * n, n);
                n_min = Math.min(temp * n, n);
            } else {
                n_max = Math.max(n_max * n, n);
                n_min = Math.min(n_min * n, n);
            }
            result = Math.max(result, n_max);
        }
        return result;
    }
}
