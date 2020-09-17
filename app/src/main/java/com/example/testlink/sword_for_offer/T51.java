package com.example.testlink.sword_for_offer;

import android.util.Log;

/**
 * Create by lxx
 * Date : 2020/9/17 10:35
 * Use by 构建乘积数组
 * 描述：
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 如:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 */
public class T51 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        solve(a);
    }

    /**
     * 花了半天理解题目的意思：B中的元素i为: A中i前面的数乘以i后面的数(除了i相乘)。
     * 理解了意思后最简单的一个法子就是全部数乘一遍，i是多少就除以多少，对应就是数组B[i]的值了，
     * 但是题目要求不能使用除法。所以这个方案不能取。换个思路：b数组中i的值都是i前面的乘积乘上
     * i后面的值。写俩遍循环，使用不同数组统计并保存出每个i前后的乘积后，在相乘即可。
     * <p>
     * [1,2,3,4,5]
     * <p>
     * temp(前):[1,1,2,6,24]
     * temp(后):[120,60,20,5,1]
     * <p>
     * 结果: [120,60,40,30,24]
     *
     * @param a
     * @return
     */
    public static int[] solve(int[] a) {
        int[] b = new int[a.length];
        int[] temp1 = new int[a.length];
        int[] temp2 = new int[a.length];
        int temp = 1;

        for (int i = 0; i < a.length; i++) {
            temp1[i] = temp;
            temp *= a[i];
        }
        temp = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            temp2[i] = temp;
            temp *= a[i];
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println("1= " + temp1[i] + "  2= " + temp2[i]);
            b[i] = temp1[i] * temp2[i];
        }
        return b;
    }

    /**
     * 思路与第一种解法一样，更加简洁。
     *
     * @param a
     * @return
     */
    public int[] solve2(int[] a) {
        int[] b = new int[a.length];
        int temp = 1;
        for (int i = 0; i < a.length; i++) {
            b[i] = temp;
            temp *= a[i];
        }
        temp = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            b[i] = temp * b[i];
            temp *= a[i];
        }
        return b;
    }
}
