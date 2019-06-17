package com.cyd.project.algorithms.linkedlist;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

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

    @Test
    public void removeAllElement(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        Random random = new Random();
        IntStream.range(0,10).forEach(num->{
            // O(1)
            linkedList.addLast(random.nextInt(5));
        });
        linkedList.print();
        linkedList.removeElement(0);
        linkedList.print();
    }

    /**
     * 倒置链表
     *
     * 在算法复杂度优先的情况下 使用栈的特性可以很简单的在O(1)复杂度完成
     *
     * addFirst方法是 O(1) 复杂度
     * removeFirst方法也是 O(1) 复杂度的  所以这个方法的性能很好
     */
    @Test
    public void invertedLinkedList(){
        LinkedList<Integer> linkedList = new LinkedList<>();
        IntStream.range(0,10).forEach(num->{
            // O(1)
            linkedList.addFirst(num);
        });
        linkedList.print();
        LinkedList<Integer> linkedList2 = new LinkedList<>();
        IntStream.range(0,10).forEach(num->{
            // O(1)
            linkedList.addFirst(linkedList2.removeFirst());
        });
        linkedList2.print();
    }
}
