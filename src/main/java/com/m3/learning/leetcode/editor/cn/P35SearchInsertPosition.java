//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找 
// 👍 626 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P35SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new P35SearchInsertPosition().new Solution();
        System.out.println(2 == solution.searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(1 == solution.searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(4 == solution.searchInsert(new int[]{4, 3, 5, 6}, 7));
        System.out.println(1 == solution.searchInsert(new int[]{1, 3, 5, 6}, 3));
        System.out.println(3 == solution.searchInsert(new int[]{1, 3, 5, 6}, 6));
        System.out.println(0 == solution.searchInsert(new int[]{1, 3, 5, 6}, 0));
        System.out.println(0 == solution.searchInsert(new int[]{1, 3, 5, 6}, 1));
        System.out.println(2 == solution.searchInsert(new int[]{1, 3, 5, 6}, 4));
        System.out.println(3 == solution.searchInsert(new int[]{2, 7, 8, 9, 10}, 9));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if(nums.length == 0) return 0;
        if(nums[len-1] == target) return len-1;
        if(nums[len-1] < target) return len;
        if(nums[0] >= target) return 0;

        int start = 0;
        int end = len-1;
        while(start < end) {
            int harf = start+(end-start+1)/2;
            if(end == harf) {
                return end;
            }
            else if(start == harf) {
                return start+1;
            }
            if(target > nums[harf]) {
                start = harf;
            }
            else if(target < nums[harf]){
                end = harf;
            }
            else {
                return harf;
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}