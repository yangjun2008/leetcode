package com.aiplayer.learning.leetcode.sn78;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）
 */
public class Subsets {
    private List<List<Integer>> result;

    public List<List<Integer>> subsets(int[] nums) {
        return solution_1(nums);
    }

    /**
     * 本题考查我们应用回溯来求解所有子集的问题，在一些算法教材中最经典的问题时求解全排列的问题，解法和这道题类似。
     * <p>
     * 此题需要特别注意的是，首先采用链表在递归过程中添加元素，在回溯时删除元素，能够有效提高时间效率。其次，给递归调用程序设计一个start参数，可以避免同一个元素被重复递归调用，达到了剪枝效果。
     * <p>
     * 最后，在结果列表中采用重新创建一个列表存储子集的结果，是因为在递归函数中列表参数只对应一个地址，采用重新创建相当于应用了深拷贝的思想，避免了结果均为空集的情况。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> solution_1(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums.length <= 0)
            return result;
        dfs(nums, 0, new LinkedList<Integer>());

        return result;
    }

    public void dfs(int[] nums, int start, LinkedList<Integer> list) {
        result.add(new ArrayList<Integer>(list));
        for (int i = start; i < nums.length; i++) {
            list.addLast(nums[i]);
            dfs(nums, i + 1, list);
            list.removeLast();
        }
    }

    /**
     * 1ms, 99.40%
     * 38.9M 5.26%
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> solution_2(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer i : nums) {
            list.add(i);
        }
        List<List<Integer>> result = getSubSets(list);
        result.add(new ArrayList<Integer>());
        return result;
    }


    private List<List<Integer>> getSubSets(List<Integer> nums) {
        int size = nums.size();
        if (size == 1) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(nums);
            return result;
        }
        List<Integer> subset = deepClone(nums);
        subset.remove(size - 1);
        List<List<Integer>> subSets = getSubSets(subset);
        List<Integer> tmp;
        int curInt = nums.get(size - 1);
        List<List<Integer>> ext = new ArrayList<List<Integer>>();
        for (List<Integer> element : subSets) {
            tmp = deepClone(element);
            tmp.add(curInt);
            ext.add(tmp);
        }
        tmp = new ArrayList<Integer>();
        tmp.add(curInt);
        ext.add(tmp);

        subSets.addAll(ext);
        return subSets;
    }

    private List<Integer> deepClone(List<Integer> source) {
        List<Integer> result = new ArrayList<Integer>();
        result.addAll(source);
        return result;
    }
}
