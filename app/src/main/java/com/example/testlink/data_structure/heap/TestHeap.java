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
        heap.insert(4);
        heap.insert(6);
        heap.insert(15);

        ArrayList<Integer> data = heap.getData();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
        System.out.println("===========================");
        heap.delete(4);
        ArrayList<Integer> data1 = heap.getData();
        for (int i = 0; i < data1.size(); i++) {
            System.out.println(data1.get(i));
        }

    }
}
