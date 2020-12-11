//给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑
//充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。 
//
// 示例 1: 
//
// 输入: [[0, 30],[5, 10],[15, 20]]
//输出: 2 
//
// 示例 2: 
//
// 输入: [[7,10],[2,4]]
//输出: 1 
// Related Topics 堆 贪心算法 排序 
// 👍 158 👎 0


package com.m3.learning.jiuyang.chafen;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P253MeetingRoomsIi {
    public static void main(String[] args) {
        Solution solution = new P253MeetingRoomsIi().new Solution();
        solution.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}});
    }

    // 算法1：优先堆
    //1 leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a));
        heap.add(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }
}
//1 leetcode submit region end(Prohibit modification and deletion)


    // 算法2：双指针
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMeetingRooms(int[][] intervals) {
            if(intervals == null || intervals.length == 0) {
                return 0;
            }
            int[] startArray = new int[intervals.length];
            int[] endArray = new int[intervals.length];
            for(int i = 0; i < intervals.length; i++) {
                startArray[i] = intervals[i][0];
                endArray[i] = intervals[i][1];
            }
            Arrays.sort(startArray);
            Arrays.sort(endArray);

            int startIdx = 0;
            int endIdx = 0;
            int count = 0;
            while(startIdx < intervals.length) {
                if(startArray[startIdx] >= endArray[endIdx]) {
                    count--;
                    endIdx++;
                }
                count++;
                startIdx++;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}