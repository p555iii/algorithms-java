package com.cyd.project.algorithms.bst;

/**
 * @Description 二分搜索树
 * @Author changyandong@e6yun.com
 * @Emoji (゜ - ゜)つ干杯
 * @Created Date: 2019/6/18 18:49
 * @ClassName BinarySearchTree
 * @Version: 1.0
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private Node root;
    private int size;

    public int size(){
        return size;
    }

    /**
     * 如果root == null 第一次进入 直接新建node = root
     * 否则进入递归方法
     * @param e
     */
//    public void add(E e){
//        if(root == null){
//            root = new Node(e);
//            size++;
//            return;
//        }
//        recursionAdd2(e,root);
//    }

    public void add(E e){
       root = recursionAdd3(e,root);
    }

    /**
     * 树向左向右递归会变成更小的一棵树，所有如果树的左孩子或者右孩子都不为空 那么就还能往下递归
     * 这时 去比较 e和 node.e的大小关系，如果e < node.e 向树的左边递归 否则向右递归
     * 当左孩子或者右孩子有一个为空时 就找到了 对应的位置
     * 这里 貌似有问题  == null的情况没判断 大小就直接插入 有问题
     * @param e
     * @param node
     */
    private void recursionAdd(E e,Node node) {
        if(node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }
        if(node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }
        if(node.e.compareTo(e) > 0){
            recursionAdd(e,node.left);
        }else if(node.e.compareTo(e) < 0){
            recursionAdd(e,node.right);
        }
    }

    public Node recursionAdd3(E e,Node node){
        // 如果node == null 说明已经找到要插入节点的位置
        if(node == null){
            size++;
            return new Node(e);
        }
        // 如果e 小于 node.e 那么左子树就等于 递归后的左子树
        if(e.compareTo(node.e) < 0){
            node.left = recursionAdd3(e,node.left);
        }else if(e.compareTo(node.e) > 0){
            node.right = recursionAdd3(e,node.right);
        }
        return node;
    }

    private void recursionAdd2(E e,Node node) {
        // 相比于我写的递归 这里判断了等于的情况下不添加到树
        // 并且判断了当前node.e 和 e的关系
        if(e.equals(node.e)){
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }
        if(node.e.compareTo(e) > 0){
            recursionAdd(e,node.left);
        }else if(node.e.compareTo(e) < 0){
            recursionAdd(e,node.right);
        }
    }

    public boolean contains(E e){
        return contains(e,root);
    }

    public void print(){
        traverse(root);
    }

    private void traverse(Node node) {
        if(node == null){
            return;
        }
        System.out.println(node.e);
        traverse(node.left);
        traverse(node.right);

    }

    /**
     * 这里要明白 每个递归都返回一个是否包含e 是否包含 由 结束语句定义
     * @param e
     * @param node
     * @return
     */
    public boolean contains(E e,Node node){
        if(node == null){
            return false;
        }

        if(e.compareTo(node.e) == 0){
            return true;
        }else if(node.e.compareTo(e) > 0){
            return contains(e,node.left);
        }else{
            return contains(e,node.right);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(44);
        binarySearchTree.add(33);
        binarySearchTree.add(55);
        binarySearchTree.add(21);
        binarySearchTree.add(35);
        binarySearchTree.add(35);
        System.out.println(binarySearchTree);

        System.out.println( binarySearchTree.contains(35));
        binarySearchTree.print();
    }
}
