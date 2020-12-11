//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
// 
//
// 说明 : 
//
// 
// 数组的长度为 [1, 20,000]。 
// 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。 
// 
// Related Topics 数组 哈希表 
// 👍 593 👎 0


package com.m3.learning.jiuyang.qianzhuihe;

import java.util.HashMap;
import java.util.Map;

public class P560SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new P560SubarraySumEqualsK().new Solution();
        System.out.println(solution.subarraySum(new int[]{1,-1,1,1,1}, 2));
    }
/*
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        sums[0] = 0;
        for(int i = 1; i < sums.length; i++) {
            sums[i] = sums[i-1]+nums[i-1];
        }
        int count = 0;
        for(int i = 1; i < sums.length; i++){
            for(int j = i-1; j>=0; j--) {
                if(sums[i] - sums[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
*/
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        int pre = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if(cache.containsKey(pre-k)) {
                System.out.println(pre-k + ", " + cache.get((pre-k)));
                count += cache.get(pre-k);
            }
            cache.put(pre, cache.getOrDefault(pre, 0)+1);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}