package com.example.testlink.sword_for_offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Create by lxx
 * Date : 2020/8/17 16:38
 * Use by 找出只出现一次的数字
 * 描述： 一个整型数组里面出了俩个数字以外，其他都出现了2次，请找出这俩个数字。
 */
public class T40 {

    /**
     * 哈希表统计法
     *
     * @param a
     */
    public static void solve(int[] a) {
        HashMap<Integer, Integer> sMap = new HashMap();

        for (int i = 0; i < a.length; i++) {
            if (sMap.containsKey(a[i])) {
                sMap.put(a[i], 0);
            } else {
                sMap.put(a[i], i);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (Integer i : sMap.values()) {
            if (i != 0) {
                list.add(i);
            }
        }
    }

}
