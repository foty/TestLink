package com.example.testlink.data_structure.heap;

import java.util.ArrayList;

/**
 * Create by lxx
 * Date : 2021/2/24 16:21
 * Use by 堆。
 */
public class MyHeap {

    /**
     * 堆：
     * 1、概念 -> 堆的本质就是完全二叉树。(完全二叉树:从根结点到倒数第二层满足完美二叉树，最后一层可以不完全填充，其叶子结点都靠左对齐)。
     * 堆的逻辑结构是一颗完全二叉树，但物理结构是顺序表(一维数组)。
     *
     * 2、种类->2种：
     * 1.最大堆(大根堆)：每个子节点的值都小于或者等于它父节点。
     * 2.最小堆(小根堆)：每个子节点的值都大于或者等于它的父节点。
     * 注意：假如在一个最大堆中，最大的那一个元素总是位于index=0的位置(从树结构说就是根节点)，但是最小的元素则未必是最后一个元素。唯一能够保证的是最小的元素
     * 是一个叶节点，但是不确定是哪一个。
     *
     * 3、用处：常常用来实现优先队列，甚至讲优先队列就是堆。在java集合中，堆是通过ArrayList数组实现的。并且有一个实现类 PriorityQueue
     */


    /**
     * 使用列表维护数据
     */
    private ArrayList<Integer> data = new ArrayList<>();
    private int size;

    public MyHeap() {

    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getSize() {
        return size;
    }


    // 堆的插入，每次总是先填满上一层，再在下一层从左往右依次插入。堆的插入步骤。

    /**
     * 思路：(最小堆)当插入一个元素时，先将这个元素插入到队列尾，然后将这个新插入的元素和它的父节点进行优先权的比较，如果比父节点的优先权要大，则和父节点
     * 呼唤位置，然后再和新的父节比较，直到比新的父节点优先权小为止。比较优先权时，最小堆与最大堆情况相反。
     */
    public void insert(int e) { // 以最小堆做例子
        //判断是不是第一个,如果不是第一个，找到下一个可插入的位置，这个位置一般都是在数据序列的最后。

        //获取准备插入位置的索引。
        int x = size;
        // 容量+1
        size++;
        if (x == 0) {
            data.add(e);
        } else {
            //插入操作，为了维持堆的性质，需要元素上浮
            data.add(-1); //  =》 先填充一个位置，使其size+1
            shiftUp(x, e);
        }
    }


    // 堆的删除,与插入操作相反，插入操作从下往上调整堆，而删除操作则从上往下调整堆。

    /**
     * 堆的删除操作通常都是删除优先权(值)最大的元素，也就是根节点。思路是将队列尾的元素值赋给根节点，队列尾结点赋值为null。然后跟据最大堆或最小堆定义，分别比
     * 较根节点下的左右2个子节点，大(最大堆选择大的)的作为新的根节点。接着处理这个根节点左右几点，重复这个过程，直到顺序正常。
     */
    public int deleteRoot() {
        if (data.size() == 0) return -9999;
        int value = data.get(0);

        //删除元素
        size--;
        data.remove(0);
        if (size != 0)
            // 下沉,为了维持堆的性质，重新选择一个根节点
            shiftDown(0, data.get(size - 1));

        return value;
    }


    /**
     * 普通删除，删除元素，不是删除根节点。
     */
    public int delete(int value) {
        //找到这个元素的位置
        int i = data.indexOf(value);
        //没有这个元素
        if (i == -1) return -9999;
        //根节点
        if (i == 0) return deleteRoot();
        //最后一个
        if (i == size - 1) {
            int delete = data.get(size - 1);
            data.remove(size - 1);
            size--;
            return delete;
        }
        int last = data.get(size - 1); // 最后一个元素
        shiftDown(i, last);
        return 0;
    }


    /**
     * 上浮。 上浮操作，就是不断对比父节点。交换位置，直达符合此堆的性质。
     */
    private void shiftUp(int index, int value) {
        while (index > 0) {
            //找到父节点索引与值。
            int parentP = (index - 1) / 2;
            int parentV = data.get(parentP);

            //父节点比子节点小，是符合最小堆定义，跳出。
            if (parentV <= value) break;
            //如果子节点值比父节点还小，需要交换。
            data.set(index, parentV);
            index = parentP;
        }
        data.set(index, value);
    }

    /**
     * 下沉。对比父节点的左右子节点。选出一个新的根节点。
     */
    private void shiftDown(int index, int value) {
        int half = size / 2; // 除以一半的值对应的索引刚好是最后一层最左边的索引。
        while (index < half) {
            //找到左右子节点的索引与值。
            int leftP = (index * 2) + 1; //  == (index + 1) * 2 - 1
            int leftV = data.get(leftP);

            int rightP = (index * 2) + 2;
            int rightV = data.get(rightP);

            // (最小)堆比较,选择较小的值<先比较左右，再比较新根节点>
            int min, minP;
            if (leftV <= rightV) {
                min = leftV;
                minP = leftP;
            } else {
                min = rightV;
                minP = rightP;
            }
            //与新根节点比较
            if (min >= value) {
                return;
            } //新节点比较小的字节点还小，符合最小堆性质，不用处理
            //交换位置
            data.set(index, min);
            index = minP;
        }
    }
}
