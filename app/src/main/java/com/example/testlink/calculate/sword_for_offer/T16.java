package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/19 15:43
 * Use by 输出反转后的链表
 */
public class T16 {

    /**
     * 双指针法(循环遍历，交换指向-方向输出即可)
     *
     * @param head
     * @return
     */
    private ListNode solve(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
