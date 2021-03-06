package com.example.testlink.design.state;

/**
 * Create by lxx
 * Date : 2020/3/17 11:18
 * Use by
 */
public class Diary {

    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void doSomthing() {
        //other something
    }

    /**
     * 朴素模式匹配算法
     *
     * 在字符匹配的过程中，不断的往回移动主串的指针来和模式串进行对比，这种行为称为回溯。
     *
     * @param s1 a,b,c,d,e,f,t,y,q,w,s,d
     * @param s2 f,t,y,q,w
     */
    private void math(String s1, String s2) {

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int count = chars1.length - chars2.length;//外层循环次数

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < chars2.length; j++) {
                if (chars1[i + j] != chars2[j]) {
                    break;
                }
                //成功条件(匹配到了最后一个，没有跳出循环，说明这个是完全匹配的2个子串)
                if (j == chars2.length-1){

                }
            }
        }


    }
}
