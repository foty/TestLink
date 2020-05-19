package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/18 17:01
 * Use by 求链表中的倒数第K个节点。
 */
public class T15 {

    /**
     * 输入一个链表，输出该链表中倒数第k个节点
     */


    /**
     * 快慢指针法
     *
     * 声明快慢2个指针，他们的差值刚好为K。当快指针遍历结束时，慢指针指向位置就是
     * 所求的节点。
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode solve(ListNode head, int k) {
        ListNode f = head, s = head;
        for (int i = 0; i < k; i++) {
            if (f != null) {
                f = f.next;
            } else {
                return null;
            }
        }
        while (f != null) {
            f = f.next;
            s = s.next;
        }
        return s;
    }
}
