//给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 
//
// 示例 1: 
//
// 输入: "bcabc"
//输出: "abc"
// 
//
// 示例 2: 
//
// 输入: "cbacdcbc"
//输出: "acdb" 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同 
// Related Topics 栈 贪心算法 
// 👍 211 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P316RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new P316RemoveDuplicateLetters().new Solution();
        System.out.println(solution.removeDuplicateLetters("bcabs"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int[] cnt = new int[26];
        for(int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i)-'a']++;
        }
        int pos = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i)<s.charAt(pos)) {
                pos = i;
            }
            if(--cnt[s.charAt(i)-'a'] ==0) {
                break;
            }
        }
        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos+1).replaceAll(""+s.charAt(pos), ""));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}