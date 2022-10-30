package hw5;
/**
 * <h1>BinarySearchTree</h1>
 * <p> In this class we Implement a BinarySearchTree class which implements SearchTree interface, using array.
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-04-12
 */
public class BinarySearchTree<E> implements SearchTree<E>{

    /** Keeps a boolean value which indicates adding operation is done or not*/
    protected boolean addReturn;
    /** Keeps a generic value which indicates last deleting data*/
    protected E deleteReturn;
    /** Keeps capacity value of our array structure*/
    protected int capacity;
    /** Keeps size value of our array structure*/
    protected int size;
    /** Keeps array structure*/
    protected E[] arr;
    /** Keeps initial capacity value of our array*/
    protected static final int INITIAL = 25;

    /**
     * This constructor initiates a BinarySearchTree with initial capacity
     * also it initialize other fields.
     */
    public BinarySearchTree(){
        addReturn = false;
        deleteReturn = null;
        capacity = INITIAL;
        size = 0;
        arr = (E[]) new Object[capacity];
    }

    /**
     * This method insert item where it belongs in tree
     * @param item -E indicates new item
     * @return - returns true if item is inserted; false if it isn't
     */
    @Override
    public boolean add(E item) {
        addReturn = false;
        int parent;
        int leftChild;
        int rightChild;
        if (size == 0){
            arr[size] = item;
            size++;
        }
        else{
            parent = 0;
            while(!addReturn){
                leftChild = 2*parent + 1;
                rightChild = 2*parent + 2;
                int compResult = ((Comparable<E>) arr[parent]).compareTo(item);
                if (compResult == 0){
                    addReturn = false;
                    System.out.println("This value is already in the tree!!");
                    break;
                }
                else if (compResult > 0){
                    parent = leftChild;
                    if (parent >= capacity){
                        reallocate();
                    }
                    if(arr[parent] == null){
                        arr[parent] = item;
                        addReturn = true;
                    }
                }
                else{
                    parent = rightChild;
                    if (parent >= capacity){
                        reallocate();
                    }
                    if(arr[parent] == null){
                        arr[parent] = item;
                        addReturn = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * reallocates our array when it when needed
     */
    private void reallocate(){
        int oldCapacity = capacity;
        capacity = capacity*2;
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < oldCapacity; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    /**
     * This method returns true when target is found in the tree.
     * @param target E- indicates target value
     * @return - returns true when target is found false otherwise.
     */
    @Override
    public boolean contains(E target) {
        boolean boolFind = false;
        int parent;
        int leftChild;
        int rightChild;
        if (arr[0] == null){
            return false;
        }
        else{
            parent = 0;
            while(parent < capacity){
                leftChild = 2*parent + 1;
                rightChild = 2*parent + 2;
                int compResult = ((Comparable<E>) arr[parent]).compareTo(target);
                if (compResult == 0){
                    boolFind = true;
//                    System.out.println("This value is in the tree!!");
                    break;
                }
                else if (compResult > 0){
                    parent = leftChild;
                    if(arr[parent] == null){
                        return false;
                    }
                }
                else{
                    parent = rightChild;
                    if(arr[parent] == null){
                        return false;
                    }
                }
            }
        }
        return boolFind;
    }

    /**
     * This method returns a reference to the data in the node that equal to target,if no such
     * node is found ,returns null.
     * @param target E- indicates target value
     * @return return null if no such node is found;otherwise ,returns reference
     */
    @Override
    public E find(E target) {
        E dataFind = null;
        int parent;
        int leftChild;
        int rightChild;
        if (arr[0] == null){
            return null;
        }
        else{
            parent = 0;
            while(parent < capacity){
                leftChild = 2*parent + 1;
                rightChild = 2*parent + 2;
                int compResult = ((Comparable<E>) arr[parent]).compareTo(target);
                if (compResult == 0){
                    dataFind = target;
                    break;
                }
                else if (compResult > 0){
                    parent = leftChild;
                    if(parent < capacity && arr[parent] == null){
                        return null;
                    }
                }
                else{
                    parent = rightChild;
                    if(parent < capacity && arr[parent] == null){
                        return null;
                    }
                }
            }
        }
        return dataFind;
    }

    /**
     * This method removes target (if found) from tree and returns it; otherwise returns null;
     * @param target E- indicates target value
     * @return - returns target if it found; otherwise,returns null
     */
    @Override
    public E delete(E target) {
        E dataRemove = null;
        int parent;
        int leftChild;
        int rightChild;
        if (arr[0] == null){
            return null;
        }
        else{
            parent = 0;
            while(parent < capacity){
                leftChild = 2*parent + 1;
                rightChild = 2*parent + 2;
                int compResult = ((Comparable<E>) arr[parent]).compareTo(target);
                if (compResult == 0){
                    dataRemove = target;
                    int tempLeft = 2*parent + 1;
                    int tempRight = 2*parent + 2;
                    if (arr[tempLeft] != null && arr[tempRight] != null){        //has two child
                        int holdR = tempLeft;
                        while(2*holdR + 2 < capacity && arr[2*holdR + 2]!= null){
                            holdR = 2*holdR + 2;
                        }
                        if ((2*holdR + 2) < capacity && arr[2*holdR + 1] == null && arr[2*holdR + 2] == null){
                            arr[parent] = arr[holdR];
                            arr[holdR] = null;
                        }
                        else if(2*holdR + 1 < capacity && arr[2*holdR + 1] != null){
                            arr[parent] = arr[holdR];
                            arr[holdR] = arr[2*holdR + 1];
                            arr[2*holdR + 1] = null;
                        }
                    }
                    else if (arr[tempLeft] != null || arr[tempRight] != null){     //has only one child
                        if (arr[tempLeft] != null){
                            arr[parent] = arr[tempLeft];
                            arr[tempLeft] = null;
                        }
                        else{
                            arr[parent] = arr[tempRight];
                            arr[tempRight] = null;
                        }
                    }
                    else{           //has no child
                        arr[parent] = null;
                    }
                    break;
                }
                else if (compResult > 0){
                    parent = leftChild;
                    if(arr[parent] == null){
                        return null;
                    }
                }
                else{
                    parent = rightChild;
                    if(arr[parent] == null){
                        return null;
                    }
                }
            }
        }
        return dataRemove;
    }
    /**
     * This method removes target (if found) from tree and returns true; otherwise, returns false;
     * @param target E- indicates target value
     * @return - returns target if it found; otherwise null
     */
    @Override
    public boolean remove(E target) {
        boolean boolRemove= false;
        int parent;
        int leftChild;
        int rightChild;
        if (arr[0] == null){
            return false;
        }
        else{
            parent = 0;
            while(parent < capacity){
                leftChild = 2*parent + 1;
                rightChild = 2*parent + 2;
                int compResult = ((Comparable<E>) arr[parent]).compareTo(target);
                if (compResult == 0){
                    boolRemove = true;
                    int tempLeft = 2*parent + 1;
                    int tempRight = 2*parent + 2;
                    if (arr[tempLeft] != null && arr[tempRight] != null){        //has two child
                        int holdR = tempLeft;
                        while(2*holdR + 2 < capacity && arr[2*holdR + 2]!= null){
                            holdR = 2*holdR + 2;
                        }
                        if ((2*holdR + 2) < capacity && arr[2*holdR + 1] == null && arr[2*holdR + 2] == null){
                            arr[parent] = arr[holdR];
                            arr[holdR] = null;
                        }
                        else if(2*holdR + 1 < capacity && arr[2*holdR + 1] != null){
                            arr[parent] = arr[holdR];
                            arr[holdR] = arr[2*holdR + 1];
                            arr[2*holdR + 1] = null;
                        }
                    }
                    else if (arr[tempLeft] != null || arr[tempRight] != null){     //has only one child
                        if (arr[tempLeft] != null){
                            arr[parent] = arr[tempLeft];
                            arr[tempLeft] = null;
                        }
                        else{
                            arr[parent] = arr[tempRight];
                            arr[tempRight] = null;
                        }
                    }
                    else{           //has no child
                        arr[parent] = null;
                    }
                    break;
                }
                else if (compResult > 0){
                    parent = leftChild;
                    if(arr[parent] == null){
                        return false;
                    }
                }
                else{
                    parent = rightChild;
                    if(arr[parent] == null){
                        return false;
                    }
                }
            }
        }
        return boolRemove;
    }

    /**
     * This methods returns String presentation of BinaryTree using an array
     * @return - String presentation of BinaryTree
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append("{");
            sb.append(arr[i]);
            sb.append("} ");
        }
        return sb.toString();
    }
}
