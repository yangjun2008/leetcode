//实现一个 MajorityChecker 的类，它应该具有下述几个 API： 
//
// 
// MajorityChecker(int[] arr) 会用给定的数组 arr 来构造一个 MajorityChecker 的实例。 
// int query(int left, int right, int threshold) 有这么几个参数：
// 
// 0 <= left <= right < arr.length 表示数组 arr 的子数组的长度。 
// 2 * threshold > right - left + 1，也就是说阈值 threshold 始终比子序列长度的一半还要大。 
// 
// 
// 
//
// 每次查询 query(...) 会返回在 arr[left], arr[left+1], ..., arr[right] 中至少出现阈值次数 thresh
//old 的元素，如果不存在这样的元素，就返回 -1。 
//
// 
//
// 示例： 
//
// MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
//majorityChecker.query(0,5,4); // 返回 1
//majorityChecker.query(0,3,3); // 返回 -1
//majorityChecker.query(2,3,2); // 返回 2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 20000 
// 1 <= arr[i] <= 20000 
// 对于每次查询，0 <= left <= right < len(arr) 
// 对于每次查询，2 * threshold > right - left + 1 
// 查询次数最多为 10000 
// 
// Related Topics 线段树 数组 二分查找 
// 👍 25 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P1157OnlineMajorityElementInSubarray {
    public static void main(String[] args) {
        int[] arr = {};
        MajorityChecker solution = new P1157OnlineMajorityElementInSubarray().new MajorityChecker(arr);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class MajorityChecker {

    public MajorityChecker(int[] arr) {

    }
    
    public int query(int left, int right, int threshold) {
        return 0;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
//leetcode submit region end(Prohibit modification and deletion)

}