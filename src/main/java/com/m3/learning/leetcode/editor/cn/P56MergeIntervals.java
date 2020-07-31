//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组 
// 👍 505 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P56MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new P56MergeIntervals().new Solution();
        int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] value = solution.merge(input);
        int[][] predict = new int[][]{{1,6},{8,10},{15,18}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,4},{4,6}};
        value = solution.merge(input);
        predict = new int[][]{{1,6}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,3}};
        value = solution.merge(input);
        predict = new int[][]{{1,3}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,1},{2,6},{3,5},{4,18}};
        value = solution.merge(input);
        predict = new int[][]{{1,1}, {2,18}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,4},{0,2},{3,5}};
        value = solution.merge(input);
        predict = new int[][]{{0,5}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{2,2},{1,3},{3,3},{3,4},{2,3},{4,5},{4,4}};
        value = solution.merge(input);
        predict = new int[][]{{1,5}};
        System.out.println(Arrays.deepEquals(value, predict));

        Solution solution2 = new P56MergeIntervals().new Solution();
        input = new int[][]{{1,4},{4,6}};
        value = solution2.merge(input);
        predict = new int[][]{{1,6}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,3}};
        value = solution2.merge(input);
        predict = new int[][]{{1,3}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,1},{2,6},{3,5},{4,18}};
        value = solution2.merge(input);
        predict = new int[][]{{1,1}, {2,18}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{1,4},{0,2},{3,5}};
        value = solution2.merge(input);
        predict = new int[][]{{0,5}};
        System.out.println(Arrays.deepEquals(value, predict));

        input = new int[][]{{2,2},{1,3},{3,3},{3,4},{2,3},{4,5},{4,4}};
        value = solution2.merge(input);
        predict = new int[][]{{1,5}};
        System.out.println(Arrays.deepEquals(value, predict));
    }

    class Solution2 {
        public int[][] merge(int[][] intervals) {
            if(intervals == null || intervals.length == 1) {
                return intervals;
            }
            Arrays.sort(intervals, (int[] a1, int[] a2) -> {
                return a1[0] - a2[0];
            });
            List<int[]> mergedList = new ArrayList<>();
            int start =0;
            int end = 0;
            while(end < intervals.length) {
                end = getNextMergeEnd(intervals, start);
                mergedList.add(doMerge(intervals, start, end));
                end++;
                start=end;
            }
            int[][] ret = new int[mergedList.size()][2];
            for(int i = 0; i < ret.length; i++) {
                ret[i] = mergedList.get(i);
            }
            return ret;
        }

        private int getNextMergeEnd(int[][] intervals, int start) {
            int max = intervals[start][1];
            int end = start;
            for(int i = start+1; i < intervals.length; i++) {
                if(max >= intervals[i][0]) {
                    max = Math.max(max, intervals[i][1]);
                    if(i == intervals.length-1) {
                        end = i;
                    }
                }
                else {
                    end = i-1;
                    break;
                }
            }
            return end;
        }

        private int[] doMerge(int[][] intervals, int start, int end) {
            int max = 0;
            for(int i = start; i <= end; i++) {
                max = Math.max(max, intervals[i][1]);
            }
            return new int[]{intervals[start][0], max};
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]); //技巧1：lambda表达式的简化写法

        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for(int[] interval : intervals) {
            if(idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval; //技巧2：动态规划，利用已保存的res的特征
            }
            else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }

        }
        return Arrays.copyOf(res, idx+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}