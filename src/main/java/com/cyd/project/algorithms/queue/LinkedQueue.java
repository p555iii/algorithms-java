package com.cyd.project.algorithms.queue;

public class LinkedQueue<E> implements Queue<E> {

    private Node<E> first,last;
    private int size;

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }
    //在链表尾部添加 Node
    @Override
    public void add(E e) {
        // 如果 last == null 说明 链表中无数据  直接 new Node(e) 并且first = last 因为链表只有 一个数据
        if(last == null){
            last = new Node<>(e);
            first = last;
        }else {
            //last 的下一个是 e  然后维护last
            last.next = new Node<>(e);
            last = last.next;
        }
        size++;
    }

    @Override
    public E peek() {
        return first.e;
    }


    public int getSize(){
        return size;
    }
    @Override
    public E poll() {
        if(size == 0){
            throw new IllegalArgumentException("无法操作");
        }

        // 队首 的对象
        Node<E> retNode = first;
        // 维护first
        first = first.next;
        // 断开 与链表的联系
        retNode.next = null;
        // 如果队列只有一个数据  poll后 就没有数据  但此时last 还指在retNode上 所以 last = null  
        if(first == null){
            last = null;
        }
        size--;
        return retNode.e;
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("linkedQueue :");
        sb.append("first ");
        Node cur = first;
        while (cur != null) {
            sb.append(cur.e+"->");
            cur = cur.next;
        }
        sb.append(" null last");
        return sb.toString();
    }

    class Node<E> {
        public E e;
        public Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
