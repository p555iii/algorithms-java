package com.cyd.project.algorithms.linkedlist;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.add(11,4);
        linkedList.set(5,4);
        linkedList.print();
        System.out.println(linkedList.contains(5));
        linkedList.remove(4);
        linkedList.print();
        linkedList.removeFirst();
        linkedList.print();
    }
}
