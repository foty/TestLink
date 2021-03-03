package com.example.testlink.dailyari;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

    public static void main(String[] args) {

        test304();
    }


    /**
     * 编号303
     */
    public static void test303() {
        int[] ints = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(ints);

        int s = numArray.sumRange2(0, 2);
        System.out.println(s);
        int s1 = numArray.sumRange2(2, 5);
        System.out.println(s1);
        int s2 = numArray.sumRange2(0, 5);
        System.out.println(s2);
    }


    public static void test304() {
        int[][] ints = new int[][]{{-4, -5}};
        NumMatrix numMatrix = new NumMatrix(ints);
//        numMatrix.sumRegion(0, 0, 0, 0);
//        numMatrix.sumRegion(0, 0, 0, 1);
        numMatrix.sumRegion(0, 1, 0, 1);
        int s = numMatrix.sumRegion2(0, 1, 0, 1);
        System.out.println(s);
    }

    /**
     * 338.比特位计数
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        /**
         * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
         */
        int[] result = new int[num + 1];
        result[0] = 0;

        for (int i = 1; i <= num; i++) {



        }
        return result;
    }
}
