package com.cyd.project.algorithms.array;

import java.util.ArrayList;
import java.util.Objects;

public class MyArrayList<T> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private int modifyCount;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
        modifyCount = 0;
    }

    public MyArrayList(T[] arr) {
        data = new Object[arr.length];
        for(int i = 0; i < arr.length; i++){
            data[i] =arr[i];
        }
        size = arr.length;

    }

    public void addLast(T t) {
        addIndex(t, size);
    }

    public void addFirst(T t) {
        addIndex(t, 0);
    }

    public void addIndex(T t, int index) {
        if (index < 0 || index > size+1) {
            throw new IllegalArgumentException("不支持操作");
        }
        // 这里需要数组扩容
        if(data.length <= size){
            resize(2*data.length);

        }
        /**
         * 这里不能写成
         *    for(int i = size ;i>=index;i--){
         *             data[i ] = data[i-1];
         *         }
         *
         *         当 index 和 size 同时等于 0 时 会出现 data[0] = data[-1];的情况
         */
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
        size++;
        modifyCount++;
    }

    private void resize(int newCapacity) {
        Object[] arr =new Object[newCapacity];
        for(int i = 0; i<size;i++){
            arr[i] = data[i];
        }
        data = arr;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        return (T) data[index];
    }

    public T set(T t, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        Object datum = data[index];
        data[index] = t;
        modifyCount++;
        return (T) datum;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, data[i])) {
                return true;
            }
        }
        return false;
    }

    public int findIndex(T t){
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, data[i])) {
                return i;
            }
        }
        return -1;
    }

    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size-1);
    }

    public boolean removeElement(T t){
        int index = findIndex(t);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    public int removeAllElement(T t){
        int index = findIndex(t);
        if(index != -1){
            remove(index);
            return removeAllElement(t)+1;
        }
        return 0;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("不支持操作");
        }
        Object datum = data[index];
        /**
         * 这里如果写成 i < size 会发生 index = capacity -1 时 data[i+1]出现数组越界
         * for (int i = index; i < size-1; i++) {
         *             data[i] = data[i + 1];
         *         }
         */
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        // 这时最后一位事实上已经移动到前一位了
        // 这时把他置位null 并且--
        // 其实这里可以不置为null 因为用户根本访问不到他 但是会占用内存  考虑gc
        data[--size] = null;
        //--size;
        modifyCount++;
        //缩容 只能在 == size/2的情况下 不然连续remove时  一直缩容

        // size/2会出现复杂度震荡 也就是 addLast 出发扩容 然后马上 removeLast 连续几次 就会一直
        // 扩容加缩容   当缩小为data.length 的 1/4时 这时再缩容  这时再添加时 也不会触发扩容
        // data.length /2 不能小于等于0 因为无法new Object[0]
        if(data.length == size/4 && data.length/2 != 0){
            resize(data.length /2);
        }
        return (T) datum;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("MyArrayList size = %s, capacity = %s\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void swap(int i, int j) {
        T value = (T)data[i];
        data[i] = data[j];
        data[j] = value;
    }
}
