package com.cyd.project.algorithms.stack;

import java.util.stream.IntStream;

public class StackTest {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack();
        IntStream.range(0,10).forEach(num->{
            arrayStack.push(num);
            arrayStack.print();
        });
        IntStream.range(0,10).forEach(num->{
            arrayStack.pop();
            arrayStack.print();
        });
    }
}
