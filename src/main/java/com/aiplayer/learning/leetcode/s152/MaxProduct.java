package com.aiplayer.learning.leetcode.s152;

public class MaxProduct {

    public int maxProduct(int[] nums) {
        int result = nums[0], n_max = 1, n_min=1;
        for(int n : nums ) {
           if(n < 0) {
               int temp = n_max;
               n_max = Math.max(n_min*n, n);
               n_min = Math.min(temp*n, n);
           }
           else {
               n_max = Math.max(n_max*n, n);
               n_min = Math.min(n_min*n, n);
           }
           result = Math.max(result, n_max);
        }
        return result;
    }
}
