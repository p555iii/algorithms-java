package com.cyd.project.algorithms.stack;

import com.cyd.project.algorithms.array.MyArrayList;

public class ArrayStack<E> implements Stack<E> {
    private MyArrayList myArrayList;

    public ArrayStack() {
        myArrayList = new MyArrayList();
    }

    public ArrayStack(int capacity){
        myArrayList = new MyArrayList(capacity);
    }


    public ArrayStack(MyArrayList myArrayList) {
        this.myArrayList = myArrayList;
    }

    @Override
    public void push(E e) {
        myArrayList.addLast(e);
    }

    @Override
    public E pop() {
        return (E)myArrayList.removeLast();
    }

    @Override
    public E peek() {
        return (E)myArrayList.get(myArrayList.size()-1);
    }

    @Override
    public int size() {
        return myArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return myArrayList.isEmpty();
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayStack :");
        sb.append("[");
        for (int i = 0; i < myArrayList.size(); i++) {
            sb.append(myArrayList.get(i));
            if (i != myArrayList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
