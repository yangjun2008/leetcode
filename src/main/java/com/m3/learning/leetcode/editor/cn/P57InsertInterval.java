//给出一个无重叠的 ，按照区间起始端点排序的区间列表。 
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出: [[1,5],[6,9]]
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出: [[1,2],[3,10],[12,16]]
//解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
// Related Topics 排序 数组 
// 👍 166 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Arrays;

public class P57InsertInterval {
    public static void main(String[] args) {
        Solution solution = new P57InsertInterval().new Solution();
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] values = solution.insert(intervals, newInterval);
        int[][] predict = {{1,5}, {6,9}};
        System.out.println(Arrays.deepEquals(values, predict));

        intervals = new int[][]{{1,2},{3,5}, {6,7},{8,10},{12,16}};
        newInterval = new int[]{4,8};
        values = solution.insert(intervals, newInterval);
        predict = new int[][]{{1,2}, {3,10}, {12, 16}};
        System.out.println(Arrays.deepEquals(values, predict));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int[][] newIntervals = new int[intervals.length+1][2];
        System.arraycopy(intervals, 0, newIntervals, 0, intervals.length);
        newIntervals[intervals.length] = newInterval;

        Arrays.sort(newIntervals, (arr1, arr2) -> arr1[0]-arr2[0]);
        int[][] res = new int[newIntervals.length][2];
        int idx = 0;
        res[idx] = newIntervals[0];
        for(int[] interval: newIntervals) {
            if(interval[0] > res[idx][1]) {
                res[++idx] = interval;
            }
            else {
                res[idx][1] = Math.max(interval[1], res[idx][1]);
            }
        }
        return Arrays.copyOf(res, idx+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}