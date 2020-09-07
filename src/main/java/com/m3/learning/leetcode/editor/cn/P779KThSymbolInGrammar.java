//在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。 
//
// 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始） 
//
// 
//例子: 
//
// 输入: N = 1, K = 1
//输出: 0
//
//输入: N = 2, K = 1
//输出: 0
//
//输入: N = 2, K = 2
//输出: 1
//
//输入: N = 4, K = 5
//输出: 1
//
//解释:
//第一行: 0
//第二行: 01
//第三行: 0110
//第四行: 01101001
// 
//
// 
//注意： 
//
// 
// N 的范围 [1, 30]. 
// K 的范围 [1, 2^(N-1)]. 
// 
// Related Topics 递归 
// 👍 78 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P779KThSymbolInGrammar {
    public static void main(String[] args) {
        Solution solution = new P779KThSymbolInGrammar().new Solution();
        System.out.println(0 == solution.kthGrammar(2, 1));
        System.out.println(1 == solution.kthGrammar(2, 2));
        System.out.println(0 == solution.kthGrammar(3, 1));
        System.out.println(1 == solution.kthGrammar(3, 2));
        System.out.println(1 == solution.kthGrammar(3, 3));
        System.out.println(0 == solution.kthGrammar(3, 4));
        System.out.println(0 == solution.kthGrammar(4, 1));
        System.out.println(1 == solution.kthGrammar(4, 2));
        System.out.println(1 == solution.kthGrammar(4, 3));
        System.out.println(0 == solution.kthGrammar(4, 4));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int kthGrammar(int N, int K) {
        if(N==1 || K<=1) return 0;

        int nGrapmer = kthGrammar(N-1, (K+1)/2);
        System.out.println("N="+N +", K="+K+", result="+nGrapmer);
        if(nGrapmer == 0) {
            if(K%2 == 0) return 1;
            return 0;
        }
        else {
            if(K%2 ==0) return 0;
            return 1;
        }
    }

    private String getKthGrapmer(int N) {
        if(N==1) {
            return "0";
        }
        String lastRow = getKthGrapmer(N-1);
        StringBuffer retBuffer = new StringBuffer();
        for(char c : lastRow.toCharArray()) {
            if(c == '0'){
                retBuffer.append("01");
            }
            else {
                retBuffer.append("10");
            }
        }
        return retBuffer.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}