//给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。 
//
// 找出 A 中的坡的最大宽度，如果不存在，返回 0 。 
//
// 
//
// 示例 1： 
//
// 输入：[6,0,8,2,1,5]
//输出：4
//解释：
//最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
// 
//
// 示例 2： 
//
// 输入：[9,8,1,0,1,9,4,0,4,1]
//输出：7
//解释：
//最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 50000 
// 0 <= A[i] <= 50000 
// 
//
// 
// Related Topics 数组 
// 👍 88 👎 0


package com.m3.learning.jiuyang.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

public class P962MaximumWidthRamp {
    public static void main(String[] args) {
        Solution solution = new P962MaximumWidthRamp().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxWidthRamp(int[] A) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for(int i = 0; i <A.length; i++) {
            if(stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }
        for(int i = A.length -1; i>=0; i--) {
            while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                int cur = stack.pop();
                res = Math.max(res, i- cur);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}