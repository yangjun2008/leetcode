//给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。 
//
// 
//
// 示例： 
//
// 输入: root = [4,2,6,1,3,null,null]
//输出: 1
//解释:
//注意，root是树节点对象(TreeNode object)，而不是数组。
//
//给定的树 [4,2,6,1,3,null,null] 可表示为下图:
//
//          4
//        /   \
//      2      6
//     / \    
//    1   3  
//
//最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。 
//
// 
//
// 注意： 
//
// 
// 二叉树的大小范围在 2 到 100。 
// 二叉树总是有效的，每个节点的值都是整数，且不重复。 
// 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 
//相同 
// 
// Related Topics 树 递归 
// 👍 65 👎 0


package com.m3.learning.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P783MinimumDistanceBetweenBstNodes {
    public static void main(String[] args) {
        Solution solution = new P783MinimumDistanceBetweenBstNodes().new Solution();
        TreeNode root = TreeNode.buildTreeFromArray(new Integer[]{4,4,6,1,3,null,-1});
        System.out.println(solution.minDiffInBST(root));

        root = TreeNode.buildTreeFromArray(new Integer[]{-1,-1});
        System.out.println(solution.minDiffInBST(root));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
    public int minDiffInBST(TreeNode root) {
        List<TreeNode> allNodes = new ArrayList<>();
        fetchAllNodes(root, allNodes);
        List<Integer> values = new ArrayList<>();
        allNodes.forEach(node -> values.add(node.val));
        Collections.sort(values,(v1, v2)->v1-v2);

        int i = 0;
        int min = Integer.MAX_VALUE;
        while(i < values.size()-1) {
            int curDiffValue = Math.abs(values.get(i)-values.get(++i));
            if(curDiffValue < min) {
                min = curDiffValue;
            }
        }
        return min;
    }

    private void fetchAllNodes(TreeNode parent, List<TreeNode> allNodes) {
        if(parent == null) {
            return;
        }
        else {
            allNodes.add(parent);
            fetchAllNodes(parent.left, allNodes);
            fetchAllNodes(parent.right, allNodes);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}