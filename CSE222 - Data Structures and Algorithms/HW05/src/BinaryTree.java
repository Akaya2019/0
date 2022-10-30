package hw5;

import java.io.Serializable;
import java.util.Scanner;
/**
 * <h1>BinaryTree</h1>
 * <p> In this class we Implement a binary tree class which implements Serializable interface, using node-link structure.
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-04-12
 */
public class BinaryTree<E> implements Serializable {

    /**
     * This class is inner Node<> class but it includes additional key information for BinaryHeap class
     * @param <E> - indicates generics
     */
    protected static class Node<E> implements Serializable {

        /** Keeps a generic value which indicates data of the Node*/
        protected E data;
        /** Keeps an int value which indicates priority value of Node*/
        protected int key;
        /** Keeps a reference which indicates left node of this node*/
        protected Node<E> left;
        /** Keeps a reference which indicates right node of this node*/
        protected Node<E> right;

        /**
         * This constructor initiates the node reference
         * @param data - indicates data value of node
         * @param keyValue - indicates priority value of node
         */
        public Node(E data , int keyValue) {
            this.data = data;
            key = keyValue;
            left = null;
            right = null;
        }

        /**
         * converts the data to String
         * @return -String which is String of data
         */
        @Override
        public String toString(){
            return data.toString();
        }
    }

    /* Root of the Binary Tree*/
    protected Node<E> root;

    /**
     * This constructor initiates the BinaryTree with null root
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * This constructor that creates a tree with a given node at the root
     * @param root - indicates given other root
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * The constructor that builds a tree from a data value and two trees
     * @param data - indicates data new root
     * @param leftSubTree - indicates left subTree of our new tree
     * @param rightSubTree - indicates right subTree of our new tree
     */
    public BinaryTree(E data , BinaryTree<E> leftSubTree , BinaryTree<E> rightSubTree) {

        root = new Node<E>(data , 0);
        if (leftSubTree != null){
            root.left = leftSubTree.root;
        }
        else
            root.left = null;

        if (rightSubTree != null){
            root.right = rightSubTree.root;
        }
        else
            root.right = null;
    }

    /**
     * This method returns left node of root as a new tree
     * @return - new BinaryTree
     */
    public BinaryTree<E> getLeftSubTree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        }
        else
            return null;
    }
    /**
     * This method returns right node of root as a new tree
     * @return - new BinaryTree
     */
    public BinaryTree<E> getRightSubTree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        }
        else
            return null;
    }

    /**
     * This method is used for indicating a node is leaf node or internal node
     * @return -boolean true if node is leaf otherwise returns false
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * The toString method generates a string representing a preorder traversal in which
     * each local root is intended a distance proportional to its depth
     * @return - String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root , 1 ,sb);
        return sb.toString();
    }
    private void preOrderTraverse(Node<E> node , int depth , StringBuilder sb) {

        for (int i = 1; i < depth; i++) {
            sb.append("    ");  // indentation
        }
        if (node == null) {
            sb.append("null\n");
        }
        else{
            sb.append(node.toString());
            sb.append("[");
            sb.append(node.key);
            sb.append("]");
            sb.append("\n");
            preOrderTraverse(node.left , depth + 1 , sb);
            preOrderTraverse(node.right , depth + 1 , sb);
        }
    }

    /**
     * This method is translates a file to tree
     * @param scan - Scanner
     * @return - new BinaryTree translated from a file
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {

        String data = scan.next();
        if(data.equals("null")){
            return null;
        }
        else{
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data , leftTree , rightTree);
        }
    }
}
