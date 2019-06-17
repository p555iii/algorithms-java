package com.cyd.project.algorithms.linkedlist;


import java.awt.*;
import java.util.Objects;

public class LinkedList<E> {

    private Node<E> first;
    private int size;

    public LinkedList() {
        first = new Node<>(null, null);
        this.size = 0;
    }

    public void addFirst(E e) {
        add(e, 0);
    }

    public void addLast(E e) {
        add(e, size);
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不支持操作");
        }

        // 添加任意位置的节点 只需要index的前一位 把他的next作为 e 的next 再把 他的next 指向 e 即可
        // 现在的first其实是虚拟节点
        // prev 要找的是 index的前一位  所以比index - 1多遍历一次 就变成了index
        Node<E> prev = first;
        //
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
//            Node<E> node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

        prev.next = new Node<>(e, prev.next);

        size++;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不支持操作");
        }
        Node res = first.next;
        for (int i = 0; i < index; i++) {
            res = res.next;
        }
        return (E) res.e;
    }

    public void reverse(){
        Node node = first.next.next;
        reverses(node);
    }

    private void reverses(Node node){
        if(node.next == null){
            first.next = node;
        }
    }

    /*public void set(E e, int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        // 找到index前一个node  使用add 类似的方法替代他
        Node prev = first;
        for(int i = 0; i < index;i++){
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
    }
*/
    public void set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        // 找到index  直接 e = e
        Node cur = first.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        Node prev = first;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node remove = prev.next;
        prev.next = remove.next;
        remove.next = null;
        size--;
        return (E) remove.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElements(Node head, int val) {
        Node prev = new Node(null,head);
        Node cur = head;
        while (cur != null){
            if(Objects.equals(cur.e,val)){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = prev.next;
                cur = cur.next;
            }
        }

    }

    /**
     * you   bug
     * @param e
     */
    public void removeElement(E e){
        Node<E> cur = first.next;
        Node<E> prev = first;

        while (cur != null){
            if(Objects.equals(cur.e,e)){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = prev.next;
                cur = cur.next;
            }
        }
    }

    public boolean contains(E e) {
        Node cur = first.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("linkedList :");
        sb.append("[");
        Node cur = first.next;
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

    public int size() {
        return size;
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
