package com.data.structures.and.algorithms.udemy.recursion.binarySubtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Finding out if a given binary tree is a sub tree of another binary tree.
 *
 * Time complexity:
 * O(n+m) siendo n el numero de elementos en el arbol 1 y m el numero de elementos
 * en el subArbol.
 *
 * Space complexity:
 * O(n+m)
 */

class Node {
    int data;
    Node left = null;
    Node right = null;

    Node(int data) {
        this.data = data;
    }
}

public class BinarySubtree {
    public static void main(String[] args) {
        /**
         *                     1
         *                 /      \
         *                2        3
         *              /  \     /   \
         *             4    5   6     7
          */
        Node root  = new Node(1);
        root.left  = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        /**
         *               4
         *             /  \
         *            5    6
         */
        Node rootSubtree = new Node(4);
        rootSubtree.left = new Node(5);
        rootSubtree.right = new Node(6);

        Node root2 = new Node(2);
        root2.left = new Node(4);
        root2.right = new Node(5);

        System.out.println(checkSubtree(root, rootSubtree));
        System.out.println(checkSubtree(root, root2));
        System.out.println(checkSubtree(root, root.left));
    }

    private static boolean checkSubtree(Node mainTree, Node isSubtree) {
        if (isSubtree == null) return true;
        if (mainTree == null) return false;

        List<Integer> first = new ArrayList<>();
        inorder(mainTree, first);
        List<Integer> second = new ArrayList<>();
        inorder(isSubtree, second);

        if (!listToString(first).contains(listToString(second))) return false;

        first.clear();
        second.clear();
        preorder(mainTree, first);
        preorder(isSubtree, second);

        return listToString(first).contains(listToString(second));
    }

    private static String listToString(List<Integer> list) {
        return list.toString()
                .replace("[","")
                .replace("]","")
                .replace(",", "");
    }

    private static void inorder(Node node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.data);
        inorder(node.right, list);
    }

    private static void preorder(Node node, List<Integer> list) {
        if (node == null) return;
        list.add(node.data);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
