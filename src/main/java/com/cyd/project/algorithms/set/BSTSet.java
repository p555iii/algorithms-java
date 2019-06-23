package com.cyd.project.algorithms.set;

import com.cyd.project.algorithms.bst.BinarySearchTree;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree binarySearchTree;

    public BSTSet(){
        binarySearchTree = new BinarySearchTree();
    }

    @Override
    public void add(E e) {
        binarySearchTree.add(e);
    }

    @Override
    public void remove(E e) {
        binarySearchTree.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }

    @Override
    public int size() {
        return binarySearchTree.size();
    }

    @Override
    public boolean contains(E e) {
        return binarySearchTree.contains(e);
    }
}
