package com.example.testlink.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/9/1 17:27
 * Use by 求 1到n的和
 * 描述：不能使用任何条件语句：for、if、else、 switch、 while、case等关键字以及条件判断
 * 语句(A?B:C)。
 */
public class T47 {

    public static void main(String[] args) {
        System.out.println(solve(5));
    }

    /**
     * 使用递归。很多判断语句以及循环语句被限制了。考察对基础知识的熟悉程度了。
     * 答案一看就懂。可当时就是想不到这样子用。
     *
     * @param n
     * @return
     */
    public static int solve(int n) {
        int sum = n;
        // 通过构造一个boolean对象来完成if else的工作内容。
        boolean result = (n > 0) && (sum += solve(n - 1)) > 0;
        return sum;
    }


    /**
     * 不使用 + - * /等四则运算做加法。
     * 思路：位运算。
     */

    /**
     * 不太懂位运算(先记录下来。。。。。)
     *
     * @param n1
     * @param n2
     * @return
     */
    public static int solve1(int n1,int n2) {
        while (n2 != 0){
            int temp = n1 ^ n2 ;

            n2 = (n1 & n2 )<<1;

            n1 = temp;
        }
        return n1;
    }
}
