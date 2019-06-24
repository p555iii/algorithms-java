package com.cyd.project.algorithms.avl;

import com.cyd.project.algorithms.map.BSTMap;
import com.cyd.project.algorithms.map.Map;
import com.cyd.project.algorithms.set.FileOperation;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    private Node root;
    private int size;


    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }


    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node add(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        // 如果e 小于 node.e 那么左子树就等于 递归后的左子树
        if (key.compareTo(node.key) < 0) {
            node.left = add(key, value, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(key, value, node.right);
        } else {
            node.value = value;
        }
        int height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        node.height = height;
        int balanceFactor = getBalanceFactor(node);


        // 平衡维护
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    private boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public boolean isBST(){
        ArrayList<K> arrayList = new ArrayList();
        inOrder(root,arrayList);
        for(int i = 0; i < arrayList.size() - 1; i++){
            if(arrayList.get(i).compareTo(arrayList.get(i + 1)) > 0){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList arrayList) {
        if(node == null){
            return;
        }
        inOrder(node.left,arrayList);
        arrayList.add(node.key);
        inOrder(node.right,arrayList);
    }


    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
        }

        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException("不存在 key");
        }
        node.value = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        return getNode(key, root);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else {

            if (node.right == null) {
                Node nodeLeft = node.left;
                node.left = null;
                size--;
                return nodeLeft;
            }

            if (node.left == null) {
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

    public Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMinValue(Node node) {
        if (node.left == null) {
            Node right;
            right = node.right;

            node.right = null;
            size--;
            return right;
        }
        node.left = removeMinValue(node.left);
        return node;
    }

    private Node getNode(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) > 0) {
            return getNode(key, node.right);
        } else {
            return getNode(key, node.left);
        }

    }

    public class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }
    }


    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println(map.isBST());
            System.out.println(map.isBalanced());
        }

        System.out.println();


    }
}