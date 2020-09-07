package com.m3.learning.jiuyang.monostack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 题目是这样的，给一个数组，返回一个大小相同的数组。
 * 返回的数组的第i个位置的值应当是，对于原数组中的第i个元素，至少往右走多少步，才能遇到一个比自己大的元素（
 * 如果之后没有比自己大的元素，或者已经是最后一个元素，则在返回数组的对应位置放上-1）。
 *
 * 简单的例子：
 *
 * input: 5,3,1,2,4
 *
 * return: -1 3 1 1 -1
 *
 * explaination:
 *
 * 对于第0个数字5，之后没有比它更大的数字，因此是-1
 * 对于第1个数字3，需要走3步才能达到4（第一个比3大的元素）
 * 对于第2和第3个数字，都只需要走1步，就可以遇到比自己大的元素
 * 对于最后一个数字4，因为之后没有更多的元素，所以是-1。
 */
public class nextExceed {
    public static void main(String[] args) {
        Solution solution = new nextExceed().new Solution();
        int[] result = solution.nextExceed(new int[]{5,3,1,2,4});
        System.out.println(Arrays.toString(result));

        Solution2 solution2 = new nextExceed().new Solution2();
        int[] result2 = solution2.nextExceed(new int[]{5,3,1,2,4});
        System.out.println(Arrays.toString(result2));
    }

    class Solution {
        public int[] nextExceed(int[] input) {
            int[] result = new int[input.length];
            Arrays.fill(result, -1);
            for(int i= 0; i < input.length; i++) {
                for(int j = i+1; j < input.length; j++) {
                    if(input[j] > input[i]) {
                        result[i] = j - i;
                        break;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 使用单调栈
     * 我们维护这样一个单调递减的stack，stack内部存的是原数组的每个index。
     * 每当我们遇到一个比当前栈顶所对应的数（就是input[monoStack.top()]）大的数的时候，我们就遇到了一个“大数“。
     * 这个”大数“比它之前多少个数大我们不知道，但是至少比当前栈顶所对应的数大。
     * 我们弹出栈内所有对应数比这个数小的栈内元素，并更新它们在返回数组中对应位置的值。因为这个栈本身的单调性，当我们栈顶元素所对应的数比这个元素大的时候，我们可以保证，栈内所有元素都比这个元素大。对于每一个元素，当它出栈的时候，说明它遇到了自己的next greater element，我们也就要更新return数组中的对应位置的值。如果一个元素一直不曾出栈，那么说明不存在next greater element，我们也就不用更新return数组了。
     */

    class Solution2 {
        public int[] nextExceed(int[] input) {
            int[] result = new int[input.length];
            Arrays.fill(result, -1);
            Stack<Integer> stack = new Stack();
            for(int i = 0; i < input.length; i++) {
                while(!stack.isEmpty() && input[i] > input[stack.peek()]) {
                    System.out.print("i="+i+", peek=" + stack.peek());
                    int top = stack.pop();
                    System.out.print(", top=" + top);
                    result[top] = i - top;

                    System.out.println("result["+top+"]="+result[top]);
                }
                stack.push(i);
            }
            return result;
        }
    }
}
