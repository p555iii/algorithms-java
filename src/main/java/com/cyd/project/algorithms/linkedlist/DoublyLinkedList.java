package com.cyd.project.algorithms.linkedlist;

/**
 * @Description
 * @Author p777iii@163.com
 * @Emoji (゜ - ゜)つ干杯
 * @Created Date: 2019/6/18 16:17
 * @ClassName DoublyLinkedList
 * @Version: 1.0
 */
public class DoublyLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public void addFirst(E e) {
       /* if (first == null) {
            first = new Node<>(e);
            first.prev = null;
            last = first;
        } else {
            first.prev = new Node<>(e);
            first.prev.next = first;
            first.prev.prev = null;
            first = first.prev;
        }
        size++;*/
       add(e,0);
    }

    public void addLast(E e){
       /* if(last == null){
            last = new Node<>(e);
            last.prev = null;
            first = last;
        } else {
            last.next = new Node<>(e);
            last.next.prev = last;
            last.next.next = null;
            last = last.next;
        }
        size++;*/
        /*add(e,size);*/
        add(e,size);
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不支持操作");
        }
        if(index == 0){
            if (first == null) {
                first = new Node<>(e);
                first.prev = null;
                last = first;
            } else {
                first.prev = new Node<>(e);
                first.prev.next = first;
                first.prev.prev = null;
                first = first.prev;
            }
            size++;
            return;
        }
        if(index == size){
            if (last == null) {
                last = new Node<>(e);
                last.prev = null;
                first = last;
            } else {
                last.next = new Node<>(e);
                last.next.prev = last;
                last.next.next = null;
                last = last.next;
            }
            size++;
            return;
        }


        Node<E> cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node<E> newNode = new Node<>(e);
        Node<E> prev = cur.prev;
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = cur;
        cur.prev = newNode;
        size++;
    }


    public int size() {
        return size;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DoublyLinkedList :");
        sb.append("[");
        Node cur = first;
        while (cur != null) {
            sb.append(cur.e);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }


    class Node<E> {
        public E e;
        public Node<E> next;
        public Node<E> prev;

        public Node(E e, Node<E> next, Node<E> prev) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(e, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addLast(5);
        doublyLinkedList.addFirst(4);
        doublyLinkedList.addFirst(3);
        doublyLinkedList.addFirst(1);
        doublyLinkedList.addLast(6);
        doublyLinkedList.print();
        doublyLinkedList.add(10,4);
        doublyLinkedList.print();
    }
}
