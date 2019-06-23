package com.cyd.project.algorithms.heap;


import com.cyd.project.algorithms.array.MyArrayList;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

    private MyArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new MyArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new MyArrayList<>();
    }

    public MaxHeap(E[] arr){
        data = new MyArrayList(arr);
        for(int i = parent(data.size() - 1); i >=0 ;i-- ){
            siftDown(i);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public E extractMax() {

        E ret = findMax();
        data.swap(0, size() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        // 如果有左孩子
        while (left(k) < size()) {
            int j = left(k);
            // 有右孩子
            if (j + 1 < size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = right(k);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(j, k);
            k = j;
        }
    }

    public E findMax() {
        return data.get(0);
    }


    public E replace(E e){
        E ret = findMax();
        data.set(e,0);
        siftDown(0);
        return ret;
    }
    public void add(E e) {
        data.addLast(e);
        siftUp(data.size() - 1);
    }


    private void siftUp(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException(" root not have parent");
        }
        return (index - 1) / 2;
    }

    private int left(int index) {
        return index * 2 + 1;
    }

    private int right(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));



        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }
}
