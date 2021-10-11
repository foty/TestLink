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

    /**
     * 273、整数转换英文表示
     */

    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"}; // 千，百万，10亿

    public String numberToWords(int num) {
        /**
         * 将非负整数 num 转换为其对应的英文表示。
         * 示例 1：
         * 输入：num = 123
         * 输出："One Hundred Twenty Three"
         * 示例 2：
         * 输入：num = 12345
         * 输出："Twelve Thousand Three Hundred Forty Five"
         * 示例 3：
         * 输入：num = 1234567
         * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
         * 示例 4：
         * 输入：num = 1234567891
         * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
         * 提示：
         * 0 <= num <= 231 - 1
         */

        /**
         * 单纯模拟，属于那种思路清晰步骤麻烦题。首先要注意的是英文的叫法是3位一组。如：
         * 123 -> 一百二十三
         * 12,123 -> 中式读法是一万二千一百二十三，而英语的读法是十二千一百二十三。看出什么是3位一组了吧。
         * 100,000,000 -> 一百百万。(就是一亿)
         * 明白了这样分组，直接模拟即可。
         */
        if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer curr = new StringBuffer();
                recursion(curr, curNum);
                curr.append(thousands[i]).append(" ");
                sb.append(curr);
            }
        }
        return sb.toString().trim();
    }

    private void recursion(StringBuffer curr, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num < 20) {
            curr.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            curr.append(tens[num / 10]).append(" ");
            recursion(curr, num % 10);
        } else {
            curr.append(singles[num / 100]).append(" Hundred ");
            recursion(curr, num % 100);
        }
    }


}
