package com.cyd.project.algorithms.queue;

import com.cyd.project.algorithms.heap.MaxHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap = new MaxHeap<>();

    @Override
    public void add(E e) {
        maxHeap.add(e);
    }

    @Override
    public E peek() {
        return maxHeap.findMax();
    }

    @Override
    public E poll() {
        return maxHeap.extractMax();
    }
}
