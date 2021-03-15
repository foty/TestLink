package com.example.testlink.calculate.sword_for_offer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Create by lxx
 * Date : 2021/2/24 16:05
 * Use by
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中
 * 位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 */
public class T61 {

    // 解法1
    private static ArrayList<Integer> list;

    // java有现成的堆使用，也就是优先队列。
    private static PriorityQueue<Integer> left; //大根堆
    private static PriorityQueue<Integer> right; //小根堆

    @RequiresApi(api = Build.VERSION_CODES.N)
    public T61() {
        list = new ArrayList<>();

        left = new PriorityQueue<>((o1, o2) -> o2 - o1); // 手动改变排序规则，实现成大根堆
        right = new PriorityQueue<>(); //默认是小根堆

    }

    public static void addNum(int num) {
        list.add(num);
        Collections.sort(list);
    }

    public static double findMedian() {
        if (list.size() % 2 == 0) {
            return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2f;
        } else {
            return list.get(list.size() / 2);
        }
    }

    /**
     * 解题如上：其实也就是求中位数。但是难点在于耗时。如果直接使用一个数组或者集合保存，插入时每次排序(冒泡)找到正确的位置，时间复杂度肯定是
     * 不符合AC的。于是用到堆这种数据结构。
     */

    // 使用堆来处理
    public static void addNum1(int num) {
        left.add(num);
        right.add(left.poll());

        //维持2个堆大小差为1.
        if (left.size() + 1 == right.size()) {
            left.add(right.poll());
        }
    }

    public static double findMedian1() {
        if (left.size() == right.size()) {

            System.out.println(left.peek());
            System.out.println(right.peek());

            return (left.peek() + right.peek()) / 2f;
        } else {
            return left.peek();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {

        T61 t = new T61();
        addNum1(1);
        addNum1(2);
        addNum1(3);
        System.out.println(findMedian1());
    }

//    执行用时：96 ms, 在所有 Java 提交中击败了19.76%的用户
//    内存消耗：49.7 MB, 在所有 Java 提交中击败了32.27%的用户
}
