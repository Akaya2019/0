package com.Ali;

/**
 * A class to represent a binary search tree with rotate operations.
 * @param <E>
 */
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E>{

    /**
     * This method make a right rotation operation on root parameter
     * @param root our root
     * @return - new root
     */
    protected Node<E> rotateRight(Node<E> root){
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    /**
     * This method make a left rotation operation on root parameter
     * @param root our root
     * @return - new root
     */
    protected Node<E> rotateLeft(Node<E> root){
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }
}
