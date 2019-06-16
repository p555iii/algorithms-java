package com.cyd.project.algorithms.queue;

import com.cyd.project.algorithms.array.MyArrayList;

public class ArrayQueue<E> implements Queue<E>{
    private MyArrayList myArrayList;

    public ArrayQueue() {
        myArrayList = new MyArrayList();
    }

    public ArrayQueue(int capacity){
        myArrayList = new MyArrayList(capacity);
    }


    @Override
    public void add(E e) {
        myArrayList.addLast(e);
    }

    @Override
    public E peek() {
        return (E)myArrayList.get(0);
    }

    @Override
    public E poll() {
        return (E)myArrayList.removeFirst();
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayQueue :");
        sb.append("top [");
        for (int i = 0; i < myArrayList.size(); i++) {
            sb.append(myArrayList.get(i));
            if (i != myArrayList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
