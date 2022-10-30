package com.Ali;

/**
 * Implements Self-balancing binary search tree which is AVLTree
 * @param <E>
 * @author Ali Kaya
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    //inner AVLNode class

    /**
     * Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     * @param <E> generic parameter which is Comparable
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;

        /** balance is right subtree height - left subtree height */
        private int balance;

        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public AVLNode(E data) {
            super(data);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString(){
            return balance +  ": " + super.toString();
        }
    }

    /** Flag to indicate that height of tree has increased. */
    private boolean increase;

    /**
     * add starter method.
     * pre: the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item){
        increase = false;
        root = add((AVLNode<E>) root , item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     * post: addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item inserted
     */
    private AVLNode<E> add(AVLNode<E> localRoot , E item){

        if (localRoot == null){
            increase = true;
            addReturn = true;
            return new AVLNode<>(item);
        }
        if (item.compareTo(localRoot.data) == 0){
            increase = false;
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0){
            localRoot.left = add( (AVLNode<E>) localRoot.left , item);

            if (increase){
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY){
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        }
        else{
            localRoot.right = add( (AVLNode<E>) localRoot.right , item);

            if (increase){
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY){
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }
    }

    /**
     * Method to rebalance left.
     * pre: localRoot is the root of an AVL subtree that is
     *      critically left-heavy.
     * post: Balance is restored.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {

        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;

        //left-right heavy
        if (leftChild.balance > AVLNode.BALANCED){

            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            if (leftRightChild.balance < AVLNode.BALANCED){     //left-right-left heavy

                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else{                                               //left-right-right heavy

                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.left = rotateLeft(leftChild);
        }
        else{ // left-left heavy

            localRoot.balance = AVLNode.BALANCED;
            leftChild.balance = AVLNode.BALANCED;
        }

        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     * @param localRoot The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> localRoot) {
        localRoot.balance--;
        if (localRoot.balance == AVLNode.BALANCED){
            increase = false;
        }
    }

    /**
     * rebalanceRight
     * pre : localRoot is the root of an AVL subtree that is
     *       more than one right heavy.
     * @post balance is restored and increase is set false
     * @param localRoot Root of the AVL subtree that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {

        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;

        //right-left heavy
        if (rightChild.balance < AVLNode.BALANCED){

            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            if (rightLeftChild.balance > AVLNode.BALANCED){           //right-left-right heavy

                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else{                                                     //right-left-left heavy
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }

            localRoot.right = rotateRight(rightChild);
        }
        else{   //right-right heavy
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }

        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Method to increment the balance field and to reset the value of
     * increase.
     * @param localRoot The AVL node whose balance is to be incremented
     */
    private void incrementBalance(AVLNode<E> localRoot) {
        localRoot.balance++;
        if (localRoot.balance == AVLNode.BALANCED){
            increase = false;
        }
    }

    /**
     * This method takes a binary search tree (BST) as a parameter and returns the AVL tree obtained by rearranging the BST.
     * The method converts the BST into an AVL tree by using rotation operations.
     * @param BST -our given Binary Search Tree
     * @return returns the AVL tree obtained by rearranging the BST.
     */
    public AVLTree<E> converter(BinarySearchTree<E> BST){
        preOrderTraverse(BST.root);
        return this;
    }

    /**
     * This recursive method is the helper method of the converter method.
     * It helps traverse over the tree.
     * @param localRoot - our bst's root parameter
     */
    private void preOrderTraverse(Node<E> localRoot) {

        if (localRoot == null) {
            return;
        }
        this.add(localRoot.data);
        preOrderTraverse(localRoot.left);
        preOrderTraverse(localRoot.right);
    }

}
