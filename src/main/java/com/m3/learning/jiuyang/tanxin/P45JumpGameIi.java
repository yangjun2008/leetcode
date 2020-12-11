//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组 
// 👍 749 👎 0


package com.m3.learning.jiuyang.tanxin;

public class P45JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new P45JumpGameIi().new Solution();
        System.out.println(solution.jump(new int[]{2,3,1,1,4}));
        System.out.println(solution.jump(new int[]{2,3,1,1,3,1,1,2}));
        System.out.println(solution.jump(new int[]{2,2,1,1,3,1,1,2}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length <=1) {
            return 0;
        }

        int length = nums.length;
        int step = 0;
        /*
        int[] steps = new int[length-1];
        steps[0] = length-1;
        int curValue = 0;
        for (int i = length-1; i >=0; i--) {
            curValue = nums[i] + i;
            for(int j = 0; j < steps.length; j++) {
                if(steps[j]>0 && curValue > steps[j]) {
                    steps[j] = i;
                    steps[j+1] = -1;
                    break;
                }
            }
            if (curValue >= length - 1) {
                curIdx = i;
                step = 1;
            }
            else if(curValue >= curIdx) {

            }
        }
        while(length>1) {
            for (int i = 0; i < length; i++) {
                if (steps[i] >= length - 1) {
                    length = i+1;
                    step++;
                    break;
                }
            }
        }*/
        return step;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}