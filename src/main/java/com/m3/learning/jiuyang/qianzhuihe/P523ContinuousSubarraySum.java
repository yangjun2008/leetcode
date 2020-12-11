//给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其
//中 n 也是一个整数。 
//
// 
//
// 示例 1： 
//
// 输入：[23,2,4,6,7], k = 6
//输出：True
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
// 
//
// 示例 2： 
//
// 输入：[23,2,6,4,7], k = 6
//输出：True
//解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
// 
//
// 
//
// 说明： 
//
// 
// 数组的长度不会超过 10,000 。 
// 你可以认为所有数字总和在 32 位有符号整数范围内。 
// 
// Related Topics 数学 动态规划 
// 👍 158 👎 0


package com.m3.learning.jiuyang.qianzhuihe;

public class P523ContinuousSubarraySum {
    public static void main(String[] args) {
        Solution solution = new P523ContinuousSubarraySum().new Solution();
        //System.out.println(solution.checkSubarraySum(new int[]{23,2,6,4,7}, 6));
        //System.out.println(solution.checkSubarraySum(new int[]{23,2,6,4,7}, 8));
        //System.out.println(solution.checkSubarraySum(new int[]{23,6,9}, 6));
        System.out.println(solution.checkSubarraySum(new int[]{0,0}, -1));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        if(k == 0) {
            for(int i = 1; i < nums.length; i++) {
                if(nums[i] == 0 && nums[i-1] ==0) {
                    return true;
                }
            }
            return false;
        }
        int[] cnt = new int[nums.length+1];
        cnt[0] = 0;
        for(int i = 1; i < nums.length+1; i++) {
            cnt[i]= cnt[i-1] + nums[i-1];
        }
        for(int i = 1; i < cnt.length; i++) {
            for(int j = 0; j < i-1; j++) {
                if((cnt[i] - cnt[j])%k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}