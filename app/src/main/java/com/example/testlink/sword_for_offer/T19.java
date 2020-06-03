package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/6/3 14:05
 * Use by 二叉树的镜像
 */
public class T19 {

    /**
     * 每个层级遍历，节点交换
     *
     * @param root
     * @return
     */
    public TreeNode solve(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        solve(root.left);
        solve(root.right);
        return  root;
    }
}
