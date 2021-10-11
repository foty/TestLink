package com.example.testlink.calculate.dailyari;

import java.util.Map;
import java.util.TreeMap;

/**
 * Create by lxx
 * Date : 2021/10/9 14:33
 * Use by
 */
class SummaryRanges {

    private TreeMap<Integer, Integer> treeMap;

    public SummaryRanges() {
        treeMap = new TreeMap<>();
    }

    public void addNum(int val) {
        /**
         * 思路：
         * 使用treemap的kv作为一个区间的开始-结束。对于一个新的数在已存在的区间有以下情况：
         * 1、已经在存在区间内；(不用处理)
         * 2、刚好是在一个区间的开始的前一个位置；(此区间开始位置为新的数)
         * 3、刚好是在一个区间的结束位置的后一个位置。(此区间的结束位置为新的数)
         * 4、刚好在已存在的2个区间的中间(2个区间就差这个数)；(2个区间整合成一个区间,开始是前区间的开始位置，结束是后区间的结束位置)
         * 5、不在已存在区间内。(独立生成一个新区间)
         */
        // ceilingEntry(key) -- 如果存在，直接返回key；若果不存在，返回所有比key大的中最小的(最接近key)。
        // floorEntry(key) -- 如果存在，直接返回key；若果不存在，返回所有比key小的中最大的(最接近key)。
        Map.Entry<Integer, Integer> enEnd = treeMap.ceilingEntry(val + 1);
        Map.Entry<Integer, Integer> enStart = treeMap.floorEntry(val);

        if (enStart != null && enStart.getKey() <= val && enStart.getValue() >= val) { //情况1
            return;
        } else {
            if (enStart != null && enStart.getValue() + 1 == val
                    && enEnd != null && enEnd.getKey() - 1 == val) { //情况4
                int newKey = enStart.getKey();
                int newValue = enEnd.getValue();
                treeMap.remove(enStart.getKey()); // 删除2个旧区间，增加新区间。
                treeMap.remove(enEnd.getKey());
                treeMap.put(newKey, newValue);
            } else if (enStart != null && enStart.getValue() + 1 == val) { //情况3
                treeMap.put(enStart.getKey(), val);
            } else if (enEnd != null && enEnd.getKey() - 1 == val) { //情况2
                treeMap.put(val, enEnd.getValue());
                treeMap.remove(enEnd.getKey());
            } else { // 情况5
                treeMap.put(val, val);
            }
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[treeMap.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> set : treeMap.entrySet()) {
            result[index][0] = set.getKey();
            result[index][1] = set.getValue();
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 2);
        map.put(2, 2);
        map.put(5, 2);
        map.put(8, 2);
        map.put(9, 2);
        map.put(13, 2);
        map.put(17, 2);
        System.out.println(map.ceilingEntry(11)); //找key=11，如果没有就找跟11最近的比11大的key
        System.out.println(map.floorEntry(4)); //找key=4,，如果不存在就找跟4最近的比4小的key。
    }


}
