//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 496 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.Stack;

public class P114FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new P114FlattenBinaryTreeToLinkedList().new Solution();
        TreeNode node = Util.getTreeNode("1,2,5,3,4,null,6");
        solution.flatten(node);
        System.out.println(Util.toString(node));

        node = Util.getTreeNode("1,2");
        solution.flatten(node);
        System.out.println(Util.toString(node));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
       if(root == null) {
           return;
       }
        flattenNode(root);
    }

    private TreeNode flattenNode(TreeNode node) {
        TreeNode rightNode = node.right;
        TreeNode pNode = node;
        if(node.left != null) {
            pNode = flattenNode(node.left);
            node.right = node.left;
            node.left = null;
        }
        if(rightNode != null) {
            pNode.right = rightNode;
            pNode = flattenNode(rightNode);
        }
        return pNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}