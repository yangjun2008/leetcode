//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。 
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window 
// 👍 657 👎 0


package com.m3.learning.jiuyang.monostack;

import java.util.ArrayDeque;
import java.util.Deque;

public class P239SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new P239SlidingWindowMaximum().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] array = new int[length-k+1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < (k-1); i++) {
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        for(int i = k-1; i < length; i++) {
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if(i - queue.peekFirst()>= k) {
                queue.removeFirst();
            }
            array[i-k+1] = nums[queue.peekFirst()];
        }
        return array;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}