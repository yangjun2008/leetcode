//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 递归 
// 👍 57 👎 0


package com.m3.learning.jiuyang.recursion;

public class POffer10IIQingWaTiaoTaiJieWenTiLcof {
    public static void main(String[] args) {
        Solution solution = new POffer10IIQingWaTiaoTaiJieWenTiLcof().new Solution();
        /*System.out.println(solution.numWays(0));
        System.out.println(solution.numWays(2));
        System.out.println(solution.numWays(3));
        System.out.println(solution.numWays(4));
        System.out.println(solution.numWays(7));*/
        System.out.println(solution.numWays(92));
        System.out.println(Integer.MAX_VALUE);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int numWays(int n) {
            int nums = climbStairs(n);
            return nums;
        }

        public int climbStairs(int n) {
            if( n == 0) {
                return 1;
            }
            if (n == 1) {
                return 1;
            } else if (n == 2) {
                return 2;
            }
            int[] cache = new int[n+1];
            cache[0] = 1;
            cache[1] = 1;
            cache[2] = 2;
            climbStairs(n, cache);
            return cache[n];
        }

        private void climbStairs(int n, int[] cache) {
            if (cache[n - 2] == 0) {
                climbStairs(n - 2, cache);
            }
            if (cache[n - 1] == 0) {
                climbStairs(n - 1, cache);
            }
            int v = (int) ((cache[n - 2] + cache[n - 1])%(1E9+7));
            cache[n] = v;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)
}