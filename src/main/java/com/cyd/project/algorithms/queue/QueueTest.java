package com.cyd.project.algorithms.queue;


import java.util.stream.IntStream;

public class QueueTest {
    public static void main(String[] args) {
        LinkedQueue arrayStack = new LinkedQueue();
        IntStream.range(0,10).forEach(num->{
            arrayStack.add(num);
            arrayStack.print();
        });
        IntStream.range(0,10).forEach(num->{
            arrayStack.poll();
            arrayStack.print();
        });
    }
}
