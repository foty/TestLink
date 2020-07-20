package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/7/20 16:10
 * Use by 从1到非负整数n中1出现的次数。
 */
public class T32 {

    public static void main(String[] args) {
        System.out.println(15 % 1);
    }

    public int solve(int n) { // 1<n<2^31
        int count = 0;

        // 1,2.3.4.5.6.7.8.9.10.
        // 11.12.13.14.15.16.17.18.19.20.
        // 21.22.23.24.25.26.27.28.29.30.
        // 31.32.33.34.35.36.37.38.39.40.

        // 111,112,113,114,115....
        // 1111,1112,1113,1114......


        // 487  10 = 48

        if (n % 10 == 1) {
            count++;  //各位数为1
        }

        for (int i = 1; i <= n; i *= 10) {
            int a = n % i;
            int b = n / i;
        }

        return count;
    }
}
