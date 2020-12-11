//有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。 
//
// 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。 
//
// 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[
//j]。 
//
// 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：s1 = "xx", s2 = "yy"
//输出：1
//解释：
//交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。 
//
// 示例 2： 
//
// 输入：s1 = "xy", s2 = "yx"
//输出：2
//解释：
//交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
//交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
//注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。 
//
// 示例 3： 
//
// 输入：s1 = "xx", s2 = "xy"
//输出：-1
// 
//
// 示例 4： 
//
// 输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 1000 
// s1, s2 只包含 'x' 或 'y'。 
// 
// Related Topics 贪心算法 字符串 
// 👍 33 👎 0


package com.m3.learning.jiuyang.tanxin;

public class P1247MinimumSwapsToMakeStringsEqual {
    public static void main(String[] args) {
        Solution solution = new P1247MinimumSwapsToMakeStringsEqual().new Solution();
        System.out.println(solution.minimumSwap("xx", "yy"));
        System.out.println(solution.minimumSwap("xy", "yx"));
        System.out.println(solution.minimumSwap("xx", "xy"));
        System.out.println(solution.minimumSwap("xxyyxyxyxx", "xyyxyxxxyx"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int minimumSwap(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return -1;
        }
        int x_sum = 0;
        int y_sum = 0;
        char curS1;
        char curS2;
        for(int i = 0; i < s1.length(); i++) {
            curS1 = s1.charAt(i);
            curS2 = s2.charAt(i);
            if(curS1 != curS2) {
                if(curS1 == 'x') {
                    x_sum++;
                }
                else {
                    y_sum++;
                }
            }
        }
        int sum = 0;
        sum += x_sum/2;
        sum += y_sum/2;
        int left_x = x_sum%2;
        int left_y = y_sum%2;
        if(left_x == 1 && left_y == 1) {
            return sum + 2;
        }
        else if(left_x == 0 && left_y == 0) {
            return sum;
        }
        else {
            return -1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}