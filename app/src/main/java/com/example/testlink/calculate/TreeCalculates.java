package com.example.testlink.calculate;


import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/4/13 17:09
 * Use by
 */
public class TreeCalculates {

    /**
     * 树结构
     */
    public static class Tree {
        int val;
        Tree left;
        Tree right;

        public Tree() {
        }

        public Tree(int x) {
            val = x;
        }
    }


    /**
     *
     * @param cen 中序遍历数组
     * @param aft 后续遍历数组
     * @return
     */
    public static Tree problem106(int[] cen, int[] aft){
        if (cen == null || aft == null) {
            return null;
        }

        if (cen.length <= 0 || aft.length <= 0) {
            return null;
        }

        Tree root = new Tree(aft[aft.length - 1]);
        for (int i = 0; i < aft.length; i++) {
            if (aft[aft.length - 1] == cen[i]) {
                root.left = problem106(Arrays.copyOfRange(cen, 0, i), Arrays.copyOfRange(aft, 0, i));

                root.right = problem106(Arrays.copyOfRange(cen, i + 1, aft.length),
                        Arrays.copyOfRange(aft, i, cen.length - 1));
            }
        }
        return root;

//        执行用时 :21 ms, 在所有 Java 提交中击败了 7.71% 的用户
//        内存消耗 :91 MB, 在所有 Java 提交中击败了 5.55% 的用户
    }
}
