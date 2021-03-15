package com.example.testlink.calculate.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2021/2/20 17:09
 * Use by 求二叉搜索树第K小的节点。限制： 1 ≤ k ≤ 二叉搜索树元素个数
 * 中序遍历(左根右)就是升序的排列序列,第K小就是K次遍历的节点。 (lc上题目是求第K大，需要倒序处理)
 * 方法1：使用集合保存有序序列，遍历取值
 * 方法2：使用一个计数器，使它==k时终止返回。
 */
public class T60 {

    /**
     * 4
     * 2     6
     * 1  3  5   8
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(6);
        root.left = n1;
        root.right = n2;
        TreeNode n11 = new TreeNode(1);
        TreeNode n12 = new TreeNode(3);
        n1.left = n11;
        n1.right = n12;
        TreeNode n21 = new TreeNode(5);
        TreeNode n22 = new TreeNode(8);
        n2.left = n21;
        n2.right = n22;
        solve(root, 1);
    }


    static List<Integer> list = new ArrayList();
    static int count = 0;
    static int k2 = 0;
    static int value = 0;

    public static int solve(TreeNode root, int k) {
        k2 = k;
        if (root == null) return 0;
        sort(root);

        //第K小
        for (int i = 0; i < list.size(); i++) {
            if (i + 1 == k) {
                return list.get(i);
            }
        }

        //求第K大:
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if (list.size() - k == i) {
//                return list.get(i);
//            }
//        }
        return 0;
    }

    private static void sort(TreeNode root) {
        if (root == null) return;
        sort(root.left);
        // 1.使用集合保存
        list.add(root.val);

        //2.使用计数器 (“左根右”求最小,如果是求最大，那么应该使用“右根左”)
        if (++count == k2) {
            value = root.val;
            return;
        }

        sort(root.right);
    }

}
