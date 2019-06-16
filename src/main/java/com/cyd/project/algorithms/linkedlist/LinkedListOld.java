package com.cyd.project.algorithms.linkedlist;

public class LinkedListOld<E> {
    private Node<E> first;
    private int size;


    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = first;
//        first = node;
        first = new Node(e, first);
        size++;
    }

    public void addLast(E e){
        add(e,size);
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不支持操作");
        }
        if(index == 0){
            addFirst(e);
        }else {
            // 当添加的不是第一个元素时  只需要index的前一位 把他的next作为 e 的next 再把 他的next 指向 e 即可
            Node<E> prev = first;
            for(int i = 0; i < index - 1; i++){
                prev = prev.next;
            }
//            Node<E> node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node<>(e,prev.next);
        }
        size++;
    }

    public E get(int index){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不支持操作");
        }
        Node res = first;
        for(int i = 0 ; i < index;i++){
            res = res.next;
        }
        return (E) res.e;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    class Node<E> {
        public E e;
        public Node<E> next;

        public Node(E e, Node<E>  next) {
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
