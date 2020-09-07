//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 哈希表 
// 👍 487 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class P739DailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new P739DailyTemperatures().new Solution();
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[]{})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if(T==null || T.length == 0) {
            return new int[0];
        }
        int[] ret = new int[T.length];
        Arrays.fill(ret, 0);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < T.length; i++) {
            while(!stack.empty() && T[stack.peek()] < T[i]) {
                int top = stack.pop();
                ret[top] = i-top;
            }
            stack.push(i);
        }
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}