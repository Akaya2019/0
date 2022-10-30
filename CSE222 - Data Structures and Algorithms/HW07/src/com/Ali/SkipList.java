package com.Ali;

import java.util.Arrays;

public class SkipList<E extends Comparable<E>> {

    //inner node class
    private static class SLNode<E>{
        SLNode<E>[] links;
        E data;

        public SLNode(int m , E data){
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }
    }
    private SLNode<E> head;
    private int maxLevel;
    private int maxCap;
    private int size;
    static final double LOG2 = Math.log(2.0);

    public SkipList(int k){
        head = null;
        maxLevel = k;
        maxCap = (int) Math.pow(2 , maxLevel) - 1;
        size = 0;
    }

    private SLNode<E>[] search (E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;

        for (int i = current.links.length-1; i >= 0; i--){
            while(current.links[i] != null && current.links[i].data.compareTo(target) < 0){
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }
    public E find(E target){
        SLNode<E>[] pred = search(target);
        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0){
            return pred[0].links[0].data;
        }
        else{
            return null;
        }
    }

    public boolean add(E item){
        int random = logRandom();
        SLNode<E>[] pred = search(item);
        SLNode<E> newNode = new SLNode<>(random , item);

        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(item) == 0){
            return false;
        }
        else{
            for (int i = 0; i < random; i++) {
                newNode.links[i] = pred[i].links[i];
                pred[i].links[i] = newNode;
            }
            size++;
            if (size > maxCap) {
                maxLevel++;
                maxCap = (int) Math.pow(2 , maxLevel) - 1;;
                head.links = Arrays.copyOf(head.links, maxLevel);
//                pred = Arrays.copyOf(update, maxLevel);
//                pred[maxLevel ‐ 1] = head;
            }
            return true;
        }
    }

    private int logRandom() {
        int r = (int) (maxCap * Math.random());//
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }

}
