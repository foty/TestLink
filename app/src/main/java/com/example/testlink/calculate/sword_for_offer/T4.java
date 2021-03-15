package com.example.testlink.calculate.sword_for_offer;

/**
 * Create by lxx
 * Date : 2020/4/10 16:07
 * Use by 替换目标中的空格。
 * <p>
 * 描述: 将一个字符串中的空格替换成“%20”。例如：当字符串为 We Are Happy.则经过替换之后的字符串为
 * We%20Are%20Happy。
 */
public class T4 {

    public String solution(String s) {
        // 此方法是否可行 (行)
//        s.replaceAll(" ","%20");
        /**
         * 可以借助 StringBuilder添加。如果转换成数组，可以优先遍历一遍，统计空格的数量方便确定
         * 复制数组的长度。
         */

        if (s == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
