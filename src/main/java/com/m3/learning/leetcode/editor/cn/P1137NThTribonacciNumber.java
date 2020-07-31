//泰波那契序列 Tn 定义如下： 
//
// T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2 
//
// 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。 
//
// 
//
// 示例 1： 
//
// 输入：n = 4
//输出：4
//解释：
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4
// 
//
// 示例 2： 
//
// 输入：n = 25
//输出：1389537
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 37 
// 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。 
// 
// Related Topics 递归 
// 👍 41 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P1137NThTribonacciNumber {
    public static void main(String[] args) {
        Solution solution = new P1137NThTribonacciNumber().new Solution();
        System.out.println(solution.tribonacci(0));
        System.out.println(solution.tribonacci(1));
        System.out.println(solution.tribonacci(2));
        System.out.println(solution.tribonacci(3));
        System.out.println(solution.tribonacci(25));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int tribonacci(int n) {
        int[] t = new int[38];
        t[0] = 0;
        t[1] = 1;
        t[2] = 1;
        if(n < 3) return t[n];

        for(int i = 3; i <= n; i++) {
            t[i] = t[i-1]+t[i-2]+t[i-3];
        }
        return t[n];
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}