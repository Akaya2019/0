package com.Ali;
/**
 * Main class tests the converter BST->AVLTree , BSTBuilder E[]->BST algorithms
 */
public class Main {

    /** A class to perform QuickSort algorithm.*/
    public static class QuickSort {
        /**
         * The main function that calls QuickSort algorithm
         * @param table - our initial array
         */
        public <T extends Comparable<T>> void sort (T[] table){
            quickSort(table , 0 , table.length-1);
        }

        /**
         * The helper function that implements QuickSort algorithm itself
         * @param table - our initial array
         * @param first - our beginning index
         * @param last - our last index
         */
        protected <T extends Comparable<T> >void quickSort(T[] table, int first, int last) {
            if (first < last) {
                int pi = partition(table, first, last);

                quickSort(table, first, pi - 1);
                quickSort(table, pi + 1, last);
            }
        }

        /**
         * This function takes last element as pivot, places
         * the pivot element at its correct position in sorted
         * array, and places all smaller (smaller than pivot)
         * to left of pivot and all greater elements to right
         * of pivot
         * @param table -our initial array
         * @param first - our start index (0)
         * @param last - our last index  (arr.length-1)
         * @return - int  returns partitioning index
         */
        public <T extends Comparable<T> > int partition(T[] table, int first, int last) {
            T pivot = table[first];
            int up = first;
            int down = last;

            do {
                while((up < last) && pivot.compareTo(table[up])>= 0){
                    up++;
                }
                while(pivot.compareTo(table[down]) < 0){
                    down--;
                }
                if (up < down){
                    swap(table , up , down);
                }

            }while(up < down);
            swap(table, first, down);
            return down;
        }

        /**
         * A utility function to swap two elements
         * @param table - our initial array
         * @param first - first element's index number
         * @param second - seconds element's index number
         */
        private <T extends Comparable<T> >void swap(T[] table , int first , int second){
            T temp = table[first];
            table[first] = table[second];
            table[second] = temp;
        }
    }

    public static int i = 0;

    /**
     * This method takes a binary tree and an array of items as input,
     * and it returns a binary search tree (BST) as output.
     * The method builds a binary search tree of n nodes.
     * The structure of the binary search tree going to be same as the structure of the binary tree.
     * @param BT -our BinaryTree parameter
     * @param arr -our arr parameter
     * @param <E> -generic parameter which is Comparable
     * @return -returns a binary search tree (BST) as output.
     */
    public static <E extends Comparable<E>> BinarySearchTree<E> BSTBuilder(BinaryTree<E> BT , E[] arr){
        i = 0;
        QuickSort testQuick = new QuickSort();
        testQuick.sort(arr);

        BinarySearchTree<E> result = new BinarySearchTree<>();
        result.root = new BinaryTree.Node<E>(null);
        result.root = InOrderTraverse(result.root , BT.root , arr , i);
        return result;
    }

    /**
     * This recursive method is the helper method of the BSTBuilder method.
     * @param myRoot-our result bst's root parameter
     * @param localRoot-our binary tree's root parameter
     * @param arr -our given array
     * @param index -
     * @param <E> -generic parameter which is Comparable
     * @return returns updated root
     */
    private static <E extends Comparable<E>> BinaryTree.Node<E> InOrderTraverse(BinaryTree.Node<E> myRoot,
                                                                                BinaryTree.Node<E> localRoot,
                                                                                E[] arr, Integer index) {
        if (localRoot == null) {
            myRoot.left = null;
            myRoot.right = null;
            return null;
        }

        myRoot.left = new BinaryTree.Node<E>(null);
        myRoot.left = InOrderTraverse(myRoot.left , localRoot.left , arr , index);

        myRoot.data = arr[i];
        i++;

        myRoot.right = new BinaryTree.Node<E>(null);
        myRoot.right = InOrderTraverse(myRoot.right , localRoot.right , arr , index);

        return myRoot;
    }

    /**
     * main method tests all methods
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println("______________________________________________________________");
        System.out.println("_________________________DRIVER CODE__________________________");
        System.out.println("______________________________________________________________\n");
        System.out.println("In this section Driver Code Tests all class' methods\n");

        System.out.println("_____________TESTING BSTBuilder method E[] -> BST____________\n");

        System.out.println("Let's create a BinaryTree<E> reference");
        BinaryTree<Integer> myBt = new BinaryTree<Integer>();
        System.out.println("Then let's add some nodes(0) in it manually");

        myBt.root = new BinaryTree.Node<>(0);
        myBt.root.left = new BinaryTree.Node<>(0);
        myBt.root.left.left = new BinaryTree.Node<>(0);
        myBt.root.left.right = new BinaryTree.Node<>(0);
        myBt.root.left.right.left = new BinaryTree.Node<>(0);
        myBt.root.left.right.right = new BinaryTree.Node<>(0);
        myBt.root.right = new BinaryTree.Node<>(0);
        myBt.root.right.right = new BinaryTree.Node<>(0);
        myBt.root.right.right.left = new BinaryTree.Node<>(0);

        System.out.println("Let's print it with toString() method\n");
        System.out.println(myBt.toString());

        System.out.println("Let's create a Integer array with some integer in it\nInteger[] myArr = {8 , 3 , 5 , 7 , 2 , 1 , 4 , 6 , 9}");
        Integer[] myArr = {8 , 3 , 5 , 7 , 2 , 1 , 4 , 6 , 9};

        System.out.println("Let's create a BinarySearchTree<E> reference and call BSTBuilder(myBt , myArr) to fill this tree");
        BinarySearchTree<Integer> bst ;
        bst = BSTBuilder(myBt , myArr);

        System.out.println("Let's print binary search tree with toString() method\n");
        System.out.println(bst.toString());

        System.out.println("Let's create another BinaryTree<E> reference");
        BinaryTree<Integer> myBt2 = new BinaryTree<Integer>();
        System.out.println("Then let's add some nodes(0) in it manually");

        myBt2.root = new BinaryTree.Node<>(0);
        myBt2.root.left = new BinaryTree.Node<>(0);
        myBt2.root.left.left = new BinaryTree.Node<>(0);
        myBt2.root.left.right = new BinaryTree.Node<>(0);
        myBt2.root.right = new BinaryTree.Node<>(0);
        myBt2.root.right.left = new BinaryTree.Node<>(0);
        myBt2.root.right.right = new BinaryTree.Node<>(0);

        System.out.println("Let's print it with toString() method\n");
        System.out.println(myBt2.toString());

        System.out.println("Let's create a Integer array with some integer in it\nInteger[] myArr2 = {75 , 35 , 55 , 65 , 25 , 15 , 45}");
        Integer[] myArr2 = {75 , 35 , 55 , 65 , 25 , 15 , 45};

        System.out.println("Let's create another BinarySearchTree<E> reference and call BSTBuilder(myBt2 , myArr2) to fill this tree");
        BinarySearchTree<Integer> bst2 ;
        bst2 = BSTBuilder(myBt2 , myArr2);

        System.out.println("Let's print binary search tree with toString() method\n");
        System.out.println(bst2.toString());

        System.out.println("_____________TESTING converter method BST->AVLTree____________\n");

        System.out.println("Let's create a AVLTree<E> reference");
        BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();

        System.out.println("Then let's add some data in it with add(E item) method");
        myBst.add(0);
        myBst.add(1);
        myBst.add(2);
        myBst.add(3);
        myBst.add(4);
        myBst.add(5);
        myBst.add(6);

        System.out.println("Let's print it with toString() method\n");
        System.out.println(myBst.toString());

        System.out.println("Let's create a AVLTree<E> reference and call converter(myBst) to fill this tree");
        BinaryTree<Integer> myAvl = new AVLTree<Integer>();
        myAvl = ((AVLTree<Integer>) myAvl).converter(myBst);
        System.out.println(myAvl.toString());
    }
}
