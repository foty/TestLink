package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/18 11:42
 * Use by O(1)时间删除链表
 */
public class T13 {
    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点
     */


    /**
     * O(n)时间解法。普通的链表删除法(刷题网上的)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode solve(ListNode head, int val) {
        if (head.val == val) return head.next;
        ListNode cur = head;
        ListNode pre = head;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null){
            pre.next = cur.next;
            cur.next = null;
        }

        return head;
    }


    /**
     * O(1)解法(书上题目，与刷题网上有一定差别)
     *
     * 这个题目有一个误区。提供链表的头结点以及要被删除的节点。注意是节点!!!!。是节点的话有两种可能。1.该节点
     * 存在于这条链表的。2.该节点不存在链表。对于2情况，返回头结点就结束。1情况下，删除节点与头结点属于同一条
     * 链表，可以很明确的知道删除接点指向的下一个节点是什么，直接操作此节点即可。(不同于给出一个值，需要删除某个节点
     * 的值等于给出的值的情况。这种情况下都是要先遍历定位节点位置。)
     *
     * @param head
     * @param delNode
     * @return
     */
    public ListNode solve2(ListNode head, ListNode delNode) {
        if (head == null || delNode == null){
            return head;
        }
        if (head.val == delNode.val){//删除是头结点
            return head.next;
        }else if (delNode.next == null){//删除是尾节点
            //在链表的尾结点，不能在用挪移大法了。老实循环吧
            ListNode node = head;
            while (node.next.next != null){
                node = node.next;
            }
            //循环后node是尾结点的前一个节点。
            node.next = null;

            return head;
        }else {
            //乾坤挪移大法。本来删除节点的操作是将被删除节点的前一个节点指向被删除节点的后一个节点。但是这样操作的话
            //就还得知道前一个节点是谁，会增加计算量。通过将后一个节点的值赋值给被删除节点，删除被删除节点的后一个
            //节点的操作整个链表的结构与正常删除节点的链表是完全一致的。故称之为乾坤挪移。
            delNode.val = delNode.next.val;//将下一个节点值给当前需要删除的值。
            delNode.next = delNode.next.next;//将要被删除的节点指向下一个节点指向的下一个节点
            // ，完成链表长度的改变。
            return head;
        }
    }
}
