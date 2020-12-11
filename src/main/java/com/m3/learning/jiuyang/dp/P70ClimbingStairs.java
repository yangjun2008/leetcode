//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1205 👎 0


package com.m3.learning.jiuyang.dp;

public class P70ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new P70ClimbingStairs().new Solution();
        System.out.println(solution.climbStairs(1));
        System.out.println(solution.climbStairs(2));
        System.out.println(solution.climbStairs(3));
        System.out.println(solution.climbStairs(4));
        System.out.println(solution.climbStairs(5));
        System.out.println(solution.climbStairs(40));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int climbStairs(int n) {
        if(n < 2 ) return n;

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            /*
             * 到达当前台阶的情况分两种：
             * 1.上一级台阶，跨1步
             * 2.上二级台阶，跨2步
             */
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}