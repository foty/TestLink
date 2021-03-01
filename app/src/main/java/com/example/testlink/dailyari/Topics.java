package com.example.testlink.dailyari;

/**
 * Create by lxx
 * Date : 2021/3/1 10:50
 * Use by
 */
public class Topics {

    public static void main(String[] args) {

        test303();
    }


    /**
     * 编号303
     */
    public static void test303() {
        int[] ints = new int[]{-2,0,3,-5,2,-1};
        NumArray numArray = new NumArray(ints);
        
        int s = numArray.sumRange2(0,2);
        System.out.println(s);
        int s1 = numArray.sumRange2(2,5);
        System.out.println(s1);
        int s2 = numArray.sumRange2(0,5);
        System.out.println(s2);
    }
}
