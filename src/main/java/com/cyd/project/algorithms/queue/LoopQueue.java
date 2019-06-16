package com.cyd.project.algorithms.queue;

/**
 * 循环队列解决 数组空间浪费的问题
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void add(E e) {
        // 当tail + 1 == front的时候 队列就已经满了
        // 但是有一种特殊情况 front = 0  tail = 7 capacity = 8 这时队列 也满了 但是tail+1 != front 这时就要用 取余的方式确定队列满了
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        // 新的tail 指向 size 原因是 原先的tail 已经不能 + 1 了 原先tail的位置相当于放弃掉了，从新的size位置开始添加
        tail = size;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        E result = data[front];
        data[front] = null;
        // 本身是front++ 但是对于循环队列 来说 要取余data.length
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return result;
    }

    public boolean isEmpty() {
        return tail == front;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircularArrayQueue2 :");
        sb.append("top [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
