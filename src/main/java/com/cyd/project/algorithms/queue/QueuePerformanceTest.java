package com.cyd.project.algorithms.queue;

public class QueuePerformanceTest {
    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("arrayQueue : time" + time1 + "s");
        // 表面上这个会快一点 但是他占用了大量的内存  因为从头到尾他都没有缩容操作
        NoRemoveQueue<Integer> circularArrayQueue = new NoRemoveQueue();
        double time2 = testQueue(circularArrayQueue, opCount);
        System.out.println("circularArrayQueue : time" + time2 + "s");

        LoopQueue<Integer> circularArrayQueue2 = new LoopQueue();
        double time3 = testQueue(circularArrayQueue2, opCount);
        System.out.println("circularArrayQueue2 : time" + time3 + "s");
    }

    public static double testQueue(Queue<Integer> queue,int opCount){
        long startTime = System.nanoTime();
        for(int i = 0; i < opCount; i++){
            queue.add(i);
        }

        for(int i = 0; i < opCount; i++){
            queue.poll();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }
}
