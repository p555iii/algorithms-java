package com.cyd.project.algorithms.stack;

import com.cyd.project.algorithms.linkedlist.LinkedList;

/**
 * 由于 链表操作 第一个元素 的复杂度是 O(1) 所以 用 first 作为栈顶
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {
    private LinkedList linkedList;

    public LinkedStack() {
        linkedList = new LinkedList();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return (E)linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return (E) linkedList.get(0);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.size() == 0;
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedStack :");
        sb.append("top [");
        for (int i = 0; i < linkedList.size(); i++) {
            sb.append(linkedList.get(i));
            if (i != linkedList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
