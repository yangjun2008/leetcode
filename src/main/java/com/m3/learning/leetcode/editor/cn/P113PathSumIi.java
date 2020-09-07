//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索 
// 👍 304 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P113PathSumIi {
    public static void main(String[] args) {
        Solution solution = new P113PathSumIi().new Solution();
        TreeNode rootNode = Util.getTreeNode("5,4,8,11,null,13,4,7,2,null,null,null,null,5,1");
        System.out.println(solution.pathSum(rootNode, 22));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = findPaths(root, sum);
        ret.forEach(e-> Collections.reverse(e));
        return ret;
    }

    public List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) {
            return ret;
        }
        if(root.left == null && root.right == null) {
            if(root.val == sum) {
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                ret.add(list);
                return ret;
            }
        }
        addChildNode(root, root.left, sum-root.val, ret);
        addChildNode(root, root.right, sum-root.val, ret);
        return ret;
    }

    private void addChildNode(TreeNode root, TreeNode child, int sum, List<List<Integer>> rootList) {
        List<List<Integer>> leftList = findPaths(child, sum);
        leftList.forEach(list -> {
            list.add(root.val);
            rootList.add(list);
        });
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}