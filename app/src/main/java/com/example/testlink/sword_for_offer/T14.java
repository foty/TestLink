package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/5/18 15:31
 * Use by 奇数放在偶数前面
 */
public class T14 {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，
     * 所有偶数位于数组的后半部分
     */

    public static void main(String[] args) {
        int[] is = new int[]{1, 2, 3, 4};
        int[] solve = solve(is);

        for (int i = 0; i < solve.length; i++) {
            System.out.print(solve[i] +"  ");
        }
    }

    /**
     * 双指针法
     *
     * @param nums
     * @return
     */
    public static int[] solve(int[] nums) {
        int p = 0;
        int q = nums.length - 1;
        while (p < q) {
            //nums[p]是奇数
            while (p < q && (nums[p] & 1) == 1) p++;
            //nums[p]是偶数
            while (p < q && (nums[q] & 1) == 0) q--;
            if (p < q) {
                int temp = nums[p];
                nums[p] = nums[q];
                nums[q] = temp;
            }
//            int a = 10, b = 11;
//            a = a ^ b;
//            b = a ^ b;// b = a ^ b ^ b = a;
//            a = a ^ b;// a = a ^ b ^ a = b
        }
        return nums;
    }
}
