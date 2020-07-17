package com.example.testlink.sword_for_offer;


/**
 * Create by lxx
 * Date : 2020/6/30 14:04
 * Use by 二叉搜索树转换成双向链表
 */
public class T27 {

    public static void main(String[] args) {
        Node root = new Node(2); //4  2
        root.left = new Node(1); // 2  1
//        root.right = new Node(6);
//        root.left.left = new Node(1);
//        root.left.right = new Node(3);
//        root.right.left = new Node(5);
//        root.right.right = new Node(7);

        Node first = solve(root, null);
        while (first.left != null) {

            System.out.println(first.val);
            first = first.left;
        }
        System.out.println(first.val);

    }

    /**
     *
     */
    public  Node treeToDoublyList(Node root) {
        if (root == null) return null;

        //第一种解法
//        Node last = solve(root, null);
//        Node first = last;
//        while (first.left != null) {
//            first = first.left;
//        }
//        first.left = last;
//        last.right = first;
//        return first;

        //第二种解法(代码更加简洁)
        solve2(root);
        head.left = prev; // 高瞻祈2见佑  厚2载翊(读:义)常由2
        prev.right = head;
        return head;
    }

    /**
     * @param root
     * @param left 前一个节点
     * @return 思路: 搜索二叉树是有规律的，要排成一个有序链其实走一遍中序遍历即可，难点是如何将每个节点
     * 的前后指针指向正确的节点。此方法通过返回找到第一个(最小)节点，从前往后跟随中序遍历，递归处理每个
     * 节点。最后返回最后一个节点。递归结束后根据指针找到第一个(双向链表)，返回第一个几点即可。
     */
    public static Node solve(Node root, Node left) {
        if (root == null) return null;

        Node before = null;
        if (root.left != null) {
            before = solve(root.left, left);
        }
        if (before == null) { // 没有左子节点，需要判断left是否为空，不为空需要处理指针指向。
            if (left != null) {
                root.left = left;
                left.right = root;
            }
        } else {//处理子父节点或者根节点
            before.right = root;
            root.left = before;
        }
        if (root.right != null) {
            return solve(root.right, root);
        }
        return root;
    }

    /**
     * 第二种解法: 核心与第一种解法一样，区别是此方法使用全局head以及prev来保存需要操作的节点，而不是
     * 通过方法返回操作节点。2者相比较，第二种判断逻辑更加少，更加接近中序遍历。
     */
    Node prev, head;
    public void solve2(Node root) {
        if (root == null) return;

        if (root.left != null) {
            solve2(root.left);
        }
        if (prev == null) { //第一个，头结点。
            head = root;
            prev = head;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        if (root.right != null) {
            solve2(root.right);
        }
    }
}
