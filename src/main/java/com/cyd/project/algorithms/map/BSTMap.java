package com.cyd.project.algorithms.map;

import com.cyd.project.algorithms.bst.BinarySearchTree;
import com.cyd.project.algorithms.set.BSTSet;
import com.cyd.project.algorithms.set.FileOperation;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private Node root;
    private int size;


    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
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
        return node;
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
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();


    }
}
