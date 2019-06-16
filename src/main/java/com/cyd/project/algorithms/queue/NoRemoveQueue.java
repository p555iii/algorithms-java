package com.cyd.project.algorithms.queue;

import com.cyd.project.algorithms.array.MyArrayList;

/**
 * 循环队列  解决 ArrayQueue poll时 每次去removeFirst 复杂度为O(n)
 * 设计了 队首索引 front
 *       队尾索引 tail
 * 维护front 和 tail的羽翼  add时 tail++
 *                         poll时 front++
 *                         当tail == front 时 队列为空
 * @param <E>
 *
 *     这里会有浪费 数组空间的问题
 */
public class NoRemoveQueue<E> implements Queue<E> {
    private MyArrayList myArrayList;
    // 队首索引
    private int front;
    // 队尾索引
    private int tail;

    public NoRemoveQueue() {
        myArrayList = new MyArrayList();
        front = 0;
        tail = 0;
    }

    public NoRemoveQueue(int capacity){
        myArrayList = new MyArrayList(capacity);
        front = 0;
        tail = 0;
    }

    @Override
    public void add(E e) {
        myArrayList.addLast(e);
        tail++;
    }

    @Override
    public E peek() {
        if(tail <= front){
            return null;
        }
        return  (E) myArrayList.get(front);
    }

    @Override
    public E poll() {
        if(tail <= front){
            return null;
        }
        return  (E) myArrayList.get(front++);
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("2222 :");
        sb.append("top [");
        for (int i = front; i < tail; i++) {
            sb.append(myArrayList.get(i));
            if (i != myArrayList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
