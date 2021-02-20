package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by lxx
 * Date : 2020/12/7 16:39
 * Use by "之"型顺序打印二叉树。
 * 之前做过一道从上到下打印二叉树的题(T23)，将偶数层顺序倒序便可。
 */
public class T58 {

    /**
     * 1
     * 2  3
     * 4 5  6 7
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rootList = new ArrayList<List<Integer>>();
        if (root == null) {
            return rootList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = 0;

            ArrayList<Integer> list = new ArrayList<>();
            for (; i < size; i++) {
                TreeNode node = queue.remove();
                list.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            if (rootList.size() % 2 == 1) { //偶数层，反转序列
                Collections.reverse(list);
            }
            rootList.add(list);
        }
        return rootList;
    }

//    执行用时：1 ms, 在所有 Java 提交中击败了99.69%的用户
//    内存消耗：38.6 MB, 在所有 Java 提交中击败了61.36%的用户
}
