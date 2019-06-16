package com.cyd.project.algorithms.array;

public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
//        myArrayList.addLast(1);
//        myArrayList.addLast(3);
//        myArrayList.addLast(4);
//        myArrayList.addLast(5);
//        myArrayList.addLast(6);
//        myArrayList.print();
//        myArrayList.addIndex(2,1);
//        myArrayList.print();
        myArrayList.addFirst(1);
        myArrayList.addFirst(2);
        myArrayList.addFirst(3);
        myArrayList.addFirst(4);
        myArrayList.addFirst(5);
        myArrayList.print();
     /*   System.out.println(myArrayList.contains(7));
        myArrayList.set(7,3);
        myArrayList.print();
        System.out.println(myArrayList.contains(7));
        System.out.println(myArrayList.remove(3));
        myArrayList.print();*/
        myArrayList.addFirst(2);
        myArrayList.print();
        System.out.println(myArrayList.removeAllElement(2));
        myArrayList.print();
        for(int i = 0 ; i < 10000;i++){
            myArrayList.addLast(i);
        }
        myArrayList.print();
    }
}
