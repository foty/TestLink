package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/8/31 14:34
 * Use by 圆圈中最后剩下的数。(约瑟夫环)
 * 描述： 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此
 * 最后剩下的数字是3。
 */
public class T46 {

    public static void main(String[] args) {
        // 10 17 2
        System.out.println(solve(5, 3));
    }


    /**
     * 0 1 2 3 4
     *
     * @param n
     * @param m
     * @return
     */
    public static int solve(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() != 1) {

            //直接找到要删除的那个位置。对应的下标，需要-1
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }

    /**
     * 数学公式法。
     * <p>
     * 先理解为什么这个数学公式可以求出答案
     * 分析：(核心关键就是逆推)
     * 从0到n-1一共n个数，就是0,1,2,3，...n-1。每次数到第m个数时删除这个数，然后重新开始数。求最后的剩下的数。
     * 首先为这些数做好编号，有因为是连续的从0到n-1。所以很容易就联想到数组或者集合的下标。而且使用下标也是非常
     * 合适的。直接分析结果，当剩下最后一个数时，他的下标是多少？必定是0。那么逆推上去，在它前一步作删除的情况下
     * 的下标是多少呢？ 是0+m，因为无论是往上加还是往下减，都是要数到第m个数。这个跟第一种解法的直接求下次删除的数
     * 下标原理一样。只不过这种思想是由下往上推。考虑到越界情况，需要将这个数取模，也就是 (0+m)%n。(n为当前存在的数字数量)。
     * 再上次的位置就是：((0+m)%n + m)%n，...。这里就能得出一个规律：上次的结果下标是这次的结果加上之间间距 m，然后取模。
     * 用f(x)，x表示剩下的数表示下标结果，即有：
     * <p>
     * 第n-1次：f(1) = 0  (n个数剩下最后一个数，一共是n-1次)
     * 第n-2次：f(2) = (0+m)%2
     * 第n-1次：f(3) = (f(2) + m)%3
     * ...
     * 第1次：f(n) = (f(n-1)+m)%n
     *
     * 于是代码思路是：循环n-1次，每次计算出对应的下标。
     *
     * @param n
     * @param m
     * @return
     */
    public int solve2(int n, int m) {

        // 最后留下数字的下标
        int index = 0;
        // 因为是逆推，i是表示当前存在的数量,剩下最后一个数时i = 1，这里从剩余2个数字往上推，所以i=2
        int i = 2;
        for (; i <= n; i++) {
            index = (index + m) % i;
        }
        // index对应的是最后留下数字的下标(编号)，但是这里n是从0开始，下标刚好对应数本身，所以不需要+1。
        return index;
    }
}
