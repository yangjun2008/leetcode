package com.m3.learning.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Util {

    public static final TreeNode getTreeNode(String input) {
        if(input == null || input.trim().length() == 0) {
            return null;
        }
        String[] nodeVal = input.trim().split(",");
        TreeNode[] tmpNodes = new TreeNode[nodeVal.length];
        if("null".equals(tmpNodes[0])) {
            return null;
        }
        else {
            tmpNodes[0] = new TreeNode(Integer.parseInt(nodeVal[0]));
        }
        for(int i = 1; i < tmpNodes.length; i++) {
            TreeNode node = "null".equals(nodeVal[i]) ? null : new TreeNode(Integer.parseInt(nodeVal[i]));
            if(i%2 == 1) {
                if(tmpNodes[(i-1)/2] != null) {
                    tmpNodes[(i - 1) / 2].left = node;
                }
            }
            else {
                if(tmpNodes[(i-1)/2] != null) {
                    tmpNodes[(i - 1) / 2].right = node;
                }
            }
            tmpNodes[i] = node;
        }
        return tmpNodes[0];
    }

    public static final String toString(TreeNode root) {
        StringBuffer buffer = new StringBuffer();
        ArrayList<Object> vals = sortFromTopToBottom(root);
        vals.forEach(val -> buffer.append(val+","));
        return buffer.toString();
    }

    public static ArrayList<Object> sortFromTopToBottom(TreeNode root) {
        ArrayList<Object> lists=new ArrayList<>();
        if(root==null)
            return lists;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode tree=queue.poll();
            if(tree == null)
                lists.add("null");
            else {
                queue.offer(tree.left);
                queue.offer(tree.right);
                lists.add(tree.val);
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        TreeNode tmpNode = getTreeNode("1,2,2,3,3,4,4,3,null,null,7,8,8,7,null,null");
        System.out.println(toString(tmpNode));
    }
}
