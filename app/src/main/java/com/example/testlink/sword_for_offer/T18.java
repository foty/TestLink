package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/28 11:52
 * Use by 判断二叉树A是否包含子树B
 */
public class T18 {


    /**
     * 思路: 基本的树的遍历比较
     *
     *
     * @param root
     * @param target
     * @return
     */
    private boolean solve(TreeNode root, TreeNode target) {

        if (root == null) return false;
        if (target == null) return false;

        if (check(root, target)) {
            // 根节点子树是相同的。
            return true;
        }

        return solve(root.left, target) || solve(root.right, target);
    }

    private boolean check(TreeNode root, TreeNode target) {

        if (target == null) return true;
        if (root == null) return false;
        if (root.val == target.val) {
            //找到相同的节点了，判断左右节点是否相等
            boolean left = check(root.left, target.left);
            boolean right = check(root.right, target.right);
            return left && right;
        }
        return false;
    }

}
