//实现 pow(x, n) ，即计算 x 的 n 次幂函数。 
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找 
// 👍 458 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P50PowxN {
    public static void main(String[] args) {
        Solution solution = new P50PowxN().new Solution();
        System.out.println(solution.myPow(2.0000, -2));
        System.out.println(solution.myPow(2, 10));
        System.out.println(solution.myPow(40, 230));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        if(x==0) return 0;
        long b = n;
        double res = 1.0;
        if(n<0) {
            x = 1/x;
            b = -b;
        }
        while(b > 0) {
            if((b&1) ==1) {
                res *=x;
            }
            x*=x;
            b>>=1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}