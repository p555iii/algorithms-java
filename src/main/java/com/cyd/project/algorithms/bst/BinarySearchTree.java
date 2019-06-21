package com.cyd.project.algorithms.bst;


import com.cyd.project.algorithms.queue.LinkedQueue;
import com.cyd.project.algorithms.queue.Queue;
import com.cyd.project.algorithms.stack.LinkedStack;
import com.cyd.project.algorithms.stack.Stack;

import java.util.ArrayList;
import java.util.List;

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

    public int size() {
        return size;
    }

    /**
     * 如果root == null 第一次进入 直接新建node = root
     * 否则进入递归方法
     *
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
    public void add(E e) {
        root = recursionAdd3(e, root);
    }

    /**
     * 树向左向右递归会变成更小的一棵树，所有如果树的左孩子或者右孩子都不为空 那么就还能往下递归
     * 这时 去比较 e和 node.e的大小关系，如果e < node.e 向树的左边递归 否则向右递归
     * 当左孩子或者右孩子有一个为空时 就找到了 对应的位置
     * 这里 貌似有问题  == null的情况没判断 大小就直接插入 有问题
     *
     * @param e
     * @param node
     */
    private void recursionAdd(E e, Node node) {
        if (node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }
        if (node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }
        if (node.e.compareTo(e) > 0) {
            recursionAdd(e, node.left);
        } else if (node.e.compareTo(e) < 0) {
            recursionAdd(e, node.right);
        }
    }

    public Node recursionAdd3(E e, Node node) {
        // 如果node == null 说明已经找到要插入节点的位置
        if (node == null) {
            size++;
            return new Node(e);
        }
        // 如果e 小于 node.e 那么左子树就等于 递归后的左子树
        if (e.compareTo(node.e) < 0) {
            node.left = recursionAdd3(e, node.left);
        } else if (e.compareTo(node.e) > 0) {
            node.right = recursionAdd3(e, node.right);
        }
        return node;
    }

    private void recursionAdd2(E e, Node node) {
        // 相比于我写的递归 这里判断了等于的情况下不添加到树
        // 并且判断了当前node.e 和 e的关系
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }
        if (node.e.compareTo(e) > 0) {
            recursionAdd(e, node.left);
        } else if (node.e.compareTo(e) < 0) {
            recursionAdd(e, node.right);
        }
    }

    public boolean contains(E e) {
        return contains(e, root);
    }

    public void preOder() {
        preOder(root);
    }


    public void removeLessValue() {
        root = removeMinValue(root);
    }

    public Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMinValue(Node node) {
        if (node.left == null) {
            Node right = node.right;

            node.right = null;
            size--;
            return right;
        }
        node.left = removeMinValue(node.left);
        return node;
    }

    public void remove(E e){
        root = remove(root,e);
    }


    /**
     * 删除树上任意节点
     *                核心思路就是找到需要删除的节点元素的节点
     *                如果他不同时包含左右子树 那么就直接删除他并且让他的子树成为他
     *                如果他同时包含左右子树 那么就
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else{

            if(node.right == null){
                Node nodeLeft = node.left;
                node.left = null;
                size--;
                return nodeLeft;
            }

            if(node.left == null){
                Node nodeRight = node.right;
                node.right = null;
                size--;
                return nodeRight;
            }

            Node successor = minimum(node);
            successor.right = removeMinValue(node);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 前序遍历非递归形式
     */
    public void preOderNR() {
        Stack<Node> stack = new LinkedStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    /**
     * 广度优先遍历
     */
    public void queue() {
        LinkedQueue<Node> queue = new LinkedQueue();
        queue.add(root);
        while (queue.getSize() != 0) {
            Node node = queue.poll();
            System.out.println(node.e);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    /**
     * 递归访问左右子树 就会变成一个排序
     */
    public void inOder() {
        inOder(root);
    }

    public void inOder(Node node) {
        if (node == null) {
            return;
        }

        inOder(node.left);
        System.out.println(node.e);
        inOder(node.right);
    }


    public void print() {
        preOder(root);
    }

    private void preOder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOder(node.left);
        preOder(node.right);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ttt(sb, 0, root);
        return sb.toString();
    }

    public void ttt(StringBuilder sb, int deep, Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.e + "--" + deep + "\n");
        ttt(sb, deep + 1, node.left);
        ttt(sb, deep + 1, node.right);
    }

    /**
     * 这里要明白 每个递归都返回一个是否包含e 是否包含 由 结束语句定义
     *
     * @param e
     * @param node
     * @return
     */
    public boolean contains(E e, Node node) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(e, node.left);
        } else {
            return contains(e, node.right);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
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
        // System.out.println(binarySearchTree);

        /*System.out.println(binarySearchTree.contains(35));
        binarySearchTree.print();
        System.out.println(binarySearchTree);

        binarySearchTree.inOder();*/
        binarySearchTree.preOder();
        System.out.println();
        binarySearchTree.preOderNR();
        System.out.println();
        binarySearchTree.queue();
        System.out.println();
        binarySearchTree.removeLessValue();
        binarySearchTree.queue();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
    }
}
