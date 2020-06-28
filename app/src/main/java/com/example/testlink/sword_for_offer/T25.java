package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/6/28 14:13
 * Use by 二叉树中和为某一值的路径
 */
public class T25 {

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     */


    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> tempList = new ArrayList<>();
    int sums;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        solve(root, sum);
        return result;
    }

    private void solve(TreeNode root, int sum) {
        if (root == null) return;
        sums = sums + root.val;
        tempList.add(root.val);

        if (sums == sum && root.left == null && root.right == null) {
            result.add(new ArrayList(tempList));
        }

        //左子节点
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        //保存数据回溯。
        sums -= tempList.get(tempList.size() - 1);
        tempList.remove(tempList.size() - 1);

    }
}
