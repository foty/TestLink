package com.example.testlink.sword_for_offer;

import java.util.List;

/**
 * Create by lxx
 * Date : 2020/10/19 15:42
 * Use by 求链表中环的入口节点
 * 题目描述：一个链表中包含环，请找出该环的入口节点。
 */
public class T54 {

    /**
     * 快慢指针法。
     * 分析。题目所求是找链表中环的入口。实际上隐藏了是否成环的问题。所以这个题目分俩个步骤:
     * 1、判断成环。
     * 2、找到环的入口。
     * 先看第一个：使用快慢指针。慢指针走一步，快指针走多步(2)。如果成环，那么快指针必然会追上慢指针；如果没有环，
     * 快指针最终会指向null。据此可以判断出有无环。
     * <p>
     * 第二个：在1的基础上知道快慢指针最终会相遇(有环的话)。所以快指针相对于慢指针是多走了n个环的长度。
     * 对于链表长度要知道：
     * 链表的总长度(节点数量) - 环的长度(数量) = 环入口节点到头结点相距的长度(数量)。
     * 换个角度看：假设A和B，2个指针。A从头结点开始，B先走过环的长度个节点。然后A，B再走过相同数量的节点后，
     * AB点相遇的位置就是环的入口节点。因为A，B相遇前，B走过了一个环的长度(n)，之后再次走到环的入口节点时，
     * B走过的长度刚好是整个链表的长度。所以B下一个节点会回到环的入口节点。而A距离B有一个环长度的距离，又是与B走过相
     * 同的长度，A走过的距离恰好就是头结点到环入口节点的长度。A下一个节点也是环的入口节点。AB就相遇了。
     * 那么怎么知道环的长度呢？其实很简单。快或慢指针再走一圈，俩指针再次相遇时走过的节点数量就是环的长度。
     * 这时让一个节点在头结点，另一个节点先走过环长度个节点，2个节点再同时走相同数量的节点，最后相遇时指向的节点
     * 就是环的入口节点。
     *
     * @param head
     * @return
     */
    public ListNode solve(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode s = head;

        ListNode f = head.next;
        while (s != f) {
            if (f == null || f.next == null) return null; // 表示没有环。
            s = s.next;
            f = f.next.next;
        }

        // 重新定义一个节点:从头结点开始。头结点走一步，s节点也走一步
        ListNode start = head;
        //如果此刻是相等的，则说明头结点为环的入口。
        if (head == s) return head;

        // <1>
        start = start.next;
        s = s.next;
        while (s != f) {
            start = start.next;
            s = s.next;
        }
        //当s再次与f相等时，表示s走了一个环的长度，同时start也走了一个环的长度。重新让s指向头结点。
        s = head;
        // 最后s与start相遇的节点就是环的入口节点。
        while (s != start) {
            start = start.next;
            s = s.next;
        }
        return start;

        //<2> 另外一种计算方式(貌似没有理解，需要用到推导公式)
//        s = head;
//        while (s != f){
//            s = s.next;
//            f= f.next;
//        }
//        return s;

    }
}
