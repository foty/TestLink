package com.example.testlink.calculate.sword_for_offer;

import java.util.Arrays;

/**
 * Create by lxx
 * Date : 2020/4/10 16:48
 * Use by 重建二叉树
 * <p>
 * 描述: 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假
 * 设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列
 * {1,2,4,7,3,5,6,8}和中序遍历序列{4,2,7,1,5,3,8,6}，则重建二叉树并返回。
 * <p>
 * 递归法
 */
public class T6 {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};

        solution(ints, ints);
    }

    public static TreeNode solution(int[] pre, int[] cen) {
        // 1、2、4、7、3、5、6、8
        // 包括 from ，不包括 to。
        //from必须在0到数组长度之间，to可能比原数组长度更长
        String[] str = new String[]{"a", "b", "c", "d", "e"};
        int[] ints = new int[]{100, 2, 3, 4, 5};
        int[] ints1 = Arrays.copyOfRange(ints, 2, 3);
//        String[] s1 = Arrays.copyOfRange(str, 5, 6);
        System.out.println(ints1.length + " - " + ints1 + " - " + ints1[0]); // 0-[I@2b193f2d-0
//        System.out.println(s1.length + " - " + s1 + " - " + s1[0]); //

        /**
         * 方法一:递归数组法
         * 根据前序遍历与中序遍历规律找到根节点，将数组分成左子树部分与右子树部分，
         * 然后左、右子树部分又分别寻找子根节点，子叶节点，后又分为左、右子树部分。
         * 依次递归。(注意结束递归条件)
         *
         */
        if (cen == null || pre == null) {
            return null;
        }

        if (cen.length <= 0 || pre.length <= 0) {
            return null;
        }

        //前序遍历的第一个就是根节点，可以直接使用
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == cen[i]) {// 确认跟节点位置，因为前序遍历第一位就是根节点，可以此作为对照,确认
                root.left = solution(Arrays.copyOfRange(pre, 1, i), Arrays.copyOfRange(cen, 0, i));

                root.right = solution(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(cen, i + 1, cen.length));
            }
        }
        return root;

        /**
         * 方法二
         *
         * 执行用时 :6 ms, 在所有 Java 提交中击败了 37.38% 的用户
         * 内存消耗 :39.4 MB, 在所有 Java 提交中击败了 100.00% 的用户
         *
         */
//        return helper(pre,0,pre.length-1,cen,0,cen.length -1);
    }

    /**
     * 递归数组法的改版-递归边界法
     *
     * @param pre    前序结果
     * @param startP 开始位置
     * @param endP   结束位置 (包含)
     * @param cen    中序结果
     * @param startC 开始位置
     * @param endC   结束位置 (包含)
     */
    private static TreeNode helper(int[] pre, int startP, int endP, int[] cen, int startC, int endC) {

        if (startP > endP || startC > endC) {
            return null;
        }

        //找根节点。前序遍历下的根节点总是在前面(后序遍历的根节点总是在后面)
        int index = 0;
        while (pre[startP] != cen[index] && index <= endC) { //不能越界
            index++;
        }

        //创建节点
        TreeNode root = new TreeNode(pre[startP]);
        //左子节点。(对于前序结果结束位置：index是从0开始的，最终的找到的是基于整个cen长度，
        // 而这里需要的是一个相对差值，所以要用index - startC)
        root.left = helper(pre, startP + 1, startP + index - startC,
                cen, startC, index - 1);
        //右子节点
        root.right = helper(pre, startP + index - startC + 1, endP,
                cen, index + 1, endC);

        return root;


    }

    /**
     * 拓展：根据中序遍历与后续遍历重写树(copy数组法)。 leetCode 106
     *
     * @param cen
     * @param aft
     * @return
     */
    public static TreeNode solution2(int[] cen, int[] aft) {

        /**
         * [9,3,15,20,7]
         *
         * [9,15,7,20,3]
         */
        if (cen == null || aft == null) {
            return null;
        }

        if (cen.length <= 0 || aft.length <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(aft[aft.length - 1]);
        for (int i = 0; i < aft.length; i++) {
            if (aft[aft.length - 1] == cen[i]) {
                root.left = solution2(Arrays.copyOfRange(cen, 0, i), Arrays.copyOfRange(aft, 0, i));

                root.right = solution2(Arrays.copyOfRange(cen, i + 1, aft.length),
                        Arrays.copyOfRange(aft, i, cen.length - 1));
            }
        }
        return root;

//        执行用时 :21 ms, 在所有 Java 提交中击败了 7.71% 的用户
//        内存消耗 :91 MB, 在所有 Java 提交中击败了 5.55% 的用户
    }

}
