//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。 
//
// 示例 1: 
//
// 
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数； 
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
// 
//
// 注意: 输入数组的长度不会超过 10000。 
// Related Topics 栈 
// 👍 182 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class P503NextGreaterElementIi {
    public static void main(String[] args) {
        Solution solution = new P503NextGreaterElementIi().new Solution();
        System.out.println(Arrays.toString(solution.nextGreaterElements(new int[]{1,2,1})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] newnums = new int[nums.length*2-1];
        System.arraycopy(nums, 0, newnums, 0, nums.length);
        System.arraycopy(nums, 0, newnums, nums.length, nums.length-1);
        int[] results = new int[newnums.length];
        Arrays.fill(results, -1);
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < newnums.length; i++) {
            while(!stack.empty() && newnums[stack.peek()] < newnums[i]) {
                int top = stack.pop();
                results[top] = newnums[i];
            }
            stack.push(i);
        }
        int[] result = new int[nums.length];
        System.arraycopy(results, 0, result, 0, nums.length);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}