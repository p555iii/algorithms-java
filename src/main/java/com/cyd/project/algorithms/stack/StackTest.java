package com.cyd.project.algorithms.stack;

import org.junit.Test;

import java.util.stream.IntStream;

public class StackTest {

    @Test
    public void invertedLinkedList(){
        LinkedStack linkedStack = new LinkedStack();
        IntStream.range(0,10).forEach(num->{
            linkedStack.push(num);

        });
        linkedStack.print();
        LinkedStack linkedStack2 = new LinkedStack();
        IntStream.range(0,10).forEach(num->{
            linkedStack2.push(linkedStack.pop());

        });
        linkedStack2.print();
    }

}
