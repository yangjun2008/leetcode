//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 638 👎 0


package com.m3.learning.leetcode.editor.cn;

public class P25ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        ListNode firstNode = new ListNode(0);
        ListNode lastNode = firstNode;
        for(int i = 1 ; i < 11; i++ ) {
            ListNode node = new ListNode(i);
            lastNode.next = node;
            lastNode = node;
        }
        /*ListNode newFirstNode = solution.reverse(firstNode);
        ListNode curNode  = newFirstNode;
        while(curNode != null) {
            System.out.println(curNode.val);
            curNode = curNode.next;
        }*/

        ListNode newFirstNode = solution.reverseKGroup(null, 3);
        ListNode curNode  = newFirstNode;
        while(curNode != null) {
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if(head == null || head.next == null) {
                return head;
            }
            ListNode curNode = head;
            ListNode thisStart = head;
            ListNode nextStart;
            ListNode newHead = null;
            int count = 0;
            while(curNode != null && count < k) {
                if(count == k-1) {
                    nextStart = curNode.next;
                    curNode.next = null;
                    ListNode thisHead = reverse(thisStart);
                    if(newHead == null) {
                        newHead = thisHead;
                    }
                    thisStart.next = reverseKGroup(nextStart, k);
                }
                curNode = curNode.next;
                count++;
            }
            if(count!= 0 && count <k) {
                return thisStart;
            }
            return newHead;
        }

        private ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode tmp;
            ListNode cur = head;
            while(cur.next != null) {
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            cur.next = pre;
            return cur;
        }
    }
}
