package com.example.testlink.data_structure.heap;

import java.util.ArrayList;

/**
 * Create by lxx
 * Date : 2021/2/25 13:51
 * Use by
 */
public class TestHeap {

    public static void main(String[] args) {
        MyHeap heap = new MyHeap();

        heap.insert(7);
        heap.insert(2);
        heap.insert(21);
        heap.insert(3);
        heap.insert(12);
        heap.insert(6);
        heap.insert(8);

        ArrayList<Integer> data = heap.getData();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

    }
}
