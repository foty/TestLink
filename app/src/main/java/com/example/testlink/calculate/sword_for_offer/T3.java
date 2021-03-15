package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/4/9 17:51
 * Use by 二维数组查找目标值
 * <p>
 * 题目描述：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列
 * 都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
 * 个整数，判断数组中是否含有该整数。
 */
public class T3 {

    public static void main(String[] args) {

        int[][] ints = {{1, 2, 3}, {2, 3, 4}, {3, 4, 5}};
        if (solution(ints, 8)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    public static boolean solution(int[][] arr, int i) {
        /**
         * 双索引循环遍历发。
         * 选定右上角或者左下角作为起点，循环遍历，比较。
         * 核心关键: 二维数组的每行每列都是递增顺序排列。意味着每个数的左边都比本身小，右边都比本身大，
         * 依此对比关键数划分目标区域。
         */
        if (arr == null || arr.length <= 0) {
            return false;
        }
        int x = 0;
        int y = arr[0].length - 1;

        while (x < arr[0].length && y >= 0) {
            if (arr[x][y] == i) {
                return true;
            }
            if (arr[x][y] > i) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

}
