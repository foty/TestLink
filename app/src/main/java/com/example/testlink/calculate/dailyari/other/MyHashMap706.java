package com.example.testlink.calculate.dailyari.other;

import java.util.Arrays;

public class MyHashMap706 {

    /**
     * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
     * 实现 MyHashMap 类：
     *
     * MyHashMap() 用空映射初始化对象
     * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其
     * 对应的值 value 。
     * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
     * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
     * 提示:
     * 0 <= key, value <= 10^6
     * 最多调用 104 次 put、get 和 remove 方法
     */

    /**
     * 跟昨天的题目差不多。偷鸡就好了
     */

    int[] save;

    public MyHashMap706() {
        save = new int[1000001];
        Arrays.fill(save,-1);
    }
    
    public void put(int key, int value) {
        save[key] = value;
    }
    
    public int get(int key) {
        return save[key];
    }
    
    public void remove(int key) {
        save[key] = -1;
    }
}