//给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。 
//
// 返回给定数组完成分隔后的最大和。 
//
// 
//
// 示例： 
//
// 输入：A = [1,15,7,9,2,5,10], K = 3
//输出：84
//解释：A 变为 [15,15,15,9,10,10,10] 
//
// 
//
// 提示： 
//
// 
// 1 <= K <= A.length <= 500 
// 0 <= A[i] <= 10^6 
// 
// Related Topics 动态规划 
// 👍 69 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P1043PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        Solution solution = new P1043PartitionArrayForMaximumSum().new Solution();
        System.out.println(solution.maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len+1];
        for(int i = 0; i <= len; i++) {
            int max  = dp[i];
            int j = i-1;
            while(i-j<=k && j >= 0) {
                max = Math.max(arr[j], max);
                dp[i] = Math.max(dp[i], dp[j]+(i-j)*max);
                j--;
            }
        }
        return dp[len];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}