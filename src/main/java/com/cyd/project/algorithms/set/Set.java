package com.cyd.project.algorithms.set;

public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean isEmpty();
    int size();
    boolean contains(E e);
}
