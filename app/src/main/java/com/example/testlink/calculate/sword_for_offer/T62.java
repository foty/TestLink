package com.example.testlink.calculate.sword_for_offer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Create by lxx
 * Date : 2021/2/26 16:01
 * Use by 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小
 */
public class T62 {

    /**
     * 思路：使用大根堆。注意的是，每次添加一个数前要移除最前面的那个数。
     *
     * @param nums
     * @param k
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] solve(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        if (k == 1) return nums;

        List<Integer> list = new ArrayList<>();

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        list.add(queue.peek());

        for (int i = k; i < nums.length; i++) {
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            list.add(queue.peek());
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
            System.out.println(list.get(i));
        }
        return ints;
    }

//    执行用时：85 ms, 在所有 Java 提交中击败了6.03%的用户
//    内存消耗：46.6 MB, 在所有 Java 提交中击败了58.90%的用户

    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, -1, -3, 5, 3, 6, 7}; //   [3,3,5,5,6,7]
        solve(ints, 3);

    }
}
