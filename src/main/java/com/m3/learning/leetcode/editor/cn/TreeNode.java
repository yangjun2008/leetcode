package com.m3.learning.leetcode.editor.cn;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode(int x) { val = x; }

      public static final TreeNode buildTreeFromArray(int[] arr) {
            if(arr == null || arr.length == 0) {
                  return null;
            }
            TreeNode[] allNodes = new TreeNode[arr.length];
            allNodes[0] = new TreeNode(arr[0]);
            for(int i = 1; i < arr.length; i++) {
                  TreeNode cur = new TreeNode(arr[i]);
                  int parentIndex = (i-1)/2;
                  if(i%2 ==1) {
                        allNodes[parentIndex].left = cur;
                  }
                  else {
                        allNodes[parentIndex].right = cur;
                  }
            }
            return allNodes[0];
      }

      public static final TreeNode buildTreeFromArray(Integer[] arr) {
            if(arr == null || arr.length == 0) {
                  return null;
            }
            TreeNode[] allNodes = new TreeNode[arr.length];
            allNodes[0] = new TreeNode(arr[0]);
            for(int i = 1; i < arr.length; i++) {
                  if(arr[i] == null) {
                        allNodes[i] = null;
                        continue;
                  }
                  TreeNode cur = new TreeNode(arr[i]);
                  allNodes[i] = cur;
                  int parentIndex = (i-1)/2;
                  if(i%2 ==1) {
                        allNodes[parentIndex].left = cur;
                  }
                  else {
                        allNodes[parentIndex].right = cur;
                  }
            }
            return allNodes[0];
      }
}
