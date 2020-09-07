//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4
// 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 942 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.HashMap;

public class P101SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        TreeNode root = Util.getTreeNode("1,2,2,3,4,4,3,null,3 ");
        System.out.println(solution.isSymmetric(root));
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
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetric(root, root.left, "0");
    }

    private boolean isSymmetric(TreeNode root, TreeNode curNode, String curPath) {
        try {
            TreeNode symmetricNode = getSymmetricNode(root, curPath);
            if(curNode == null) {
                return symmetricNode == null;
            }
            else if(symmetricNode == null){
                return false;
            }
            return curNode.val == symmetricNode.val && isSymmetric(root, curNode.left, curPath + '0') && isSymmetric(root, curNode.right, curPath + '1');
        }
        catch (Exception e) {
            return false;
        }
    }

    private TreeNode getSymmetricNode(TreeNode root, String srcPath) throws Exception {
        StringBuffer snkPathBuffer  = new StringBuffer(srcPath.length());
        srcPath.chars().forEach( e -> snkPathBuffer.append(e=='0' ? '1' : '0'));
        TreeNode curNode = root;
        int[] chars = snkPathBuffer.chars().toArray();
        for(int i=0; i < chars.length; i++) {
            if(chars[i] == '0') {
                curNode = curNode.left;
            }
            else {
                curNode = curNode.right;
            }
            if(curNode == null && i<chars.length-1) {
                throw new Exception("Not found.");
            }
        }
        return curNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}