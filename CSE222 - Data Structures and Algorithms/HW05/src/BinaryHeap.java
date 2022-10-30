package hw5;
/**
 * <h1>BinaryHeap</h1>
 * <p> In this class we Implement a binary heap class which extends the BinaryTree class, using node-link structure.
 * BinaryHeap satisfy the structural property of being a complete tree besides the heap order property.
 * Also it implements some additional methods such as incrementPriority and mergeOperation.
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-04-12
 */
public class BinaryHeap<E> extends BinaryTree<E> {

    /** Keeps a boolean value which indicates adding operation is done or not*/
    private boolean addBool;
    /** Keeps a boolean value which indicates finding operation is done or not*/
    private boolean findBool;
    /** Keeps a generic value which indicates last deleting data*/
    private E deleteReturn;
    /** Keeps a node which indicates lastNode of the heap*/
    private Node<E> lastNode;
    /** Keeps an integer value which indicates key of each node*/
    private int keyValue;
    /** Keeps an integer value which indicates maximum depth of the heap*/
    private int maxDepth;
    private E tempData = null;

    /**
     * This constructor initiates the BinaryHeap with 0 depth and element.
     */
    public BinaryHeap() {
        super();
        addBool = false;
        lastNode = null;
        keyValue = -1;
        maxDepth = 0;
    }

    /**
     * This method is used for adding new elements in BinaryHeap
     * @param item -E element to be added to the BinaryHeap
     * @return boolean - indicates adding operation is done or not
     */
    public boolean offer(E item) {
        root = offer(root, item);
        return addBool;
    }
    /**
     * This method is called from public offer method
     * @param localRoot Node element which indicates root of the heap initially
     * @param item -E element to be added to the BinaryHeap
     * @return -Node<E> returns the added root
     */
    private Node<E> offer(Node<E> localRoot, E item) {
        addBool = false;
        preOrderTraverse(localRoot , 0);
        if (localRoot == null){
            keyValue++;
            localRoot = new Node<E>(item , keyValue);
            lastNode = localRoot;
            return localRoot;
        }
        else if(localRoot.left == null){
            keyValue++;
            localRoot.left = new Node<E>(item , keyValue);
            lastNode = localRoot.left;
            return localRoot;
        }
        else{
            keyValue++;
            preOrderAdd(localRoot , 0 , item , keyValue);
            if (!addBool){
                Node<E> temp = localRoot;
                for (int i = 0; i < maxDepth; i++) {
                    temp = temp.left;
                }
                temp.left = new Node<>(item , keyValue);
            }
            return localRoot;
        }
    }

    /**
     * This method is called from private offer method
     * @param node - indicates subTrees
     * @param depth - indicates depth of this level
     * @param item  - E element to be added to the BinaryHeap
     * @param keyValue - key value of adding element
     * @return -Node<E> returns the added root lastly
     */
    private Node<E> preOrderAdd(Node<E> node , int depth , E item , int keyValue) {

        if (maxDepth == depth && node == null) {
            node = new Node<E>(item, keyValue);
            lastNode = node;
            addBool = true;
            return node;
        }
        else if(node != null){
            node.left = preOrderAdd(node.left , depth + 1 , item , keyValue);
            if (!addBool)
                node.right = preOrderAdd(node.right , depth + 1 , item , keyValue);
        }
        return node;
    }

    /**
     * This method is used for finding maximum depth and lastNode of the BinaryHeap
     * @param node - indicates subTrees
     * @param depth - indicates depth of this level
     */
    private void preOrderTraverse(Node<E> node , int depth) {

        if (maxDepth <= depth && node != null) {
            maxDepth = depth;
            lastNode = node;
        }
        if(node != null){
            preOrderTraverse(node.left , depth + 1);
            preOrderTraverse(node.right , depth + 1);
        }
    }

    /**
     * This method deletes root element of heap,then fixes the order of heap using fixHeap method
     * @return - returns last deleted element
     */
    public E poll(){
        root = poll(root);
        root = fixHeap();
        return deleteReturn;
    }

    /**
     * This method is called from public poll method
     * @param localRoot -indicates root of the heap initially
     * @return - returns the root lastly
     */
    private Node<E> poll(Node<E> localRoot){

        if (root != null){
            deleteReturn = root.data;
            root.data = lastNode.data;
            root.key = lastNode.key;
            localRoot = preOrderDelete(localRoot , 0 , lastNode.key);
            return localRoot;
        }
        else{
            return null;
        }
    }

    /**
     *  This method traverses the heap by using preorder traverse and deletes the specified element
     * @param node - indicates subTrees
     * @param depth - indicates depth of this level
     * @param keyOfLast - key value of adding element
     * @return - returns the root lastly
     */
    private Node<E> preOrderDelete(Node<E> node , int depth , int keyOfLast) {

        if (maxDepth == depth && node != null && node.key == keyOfLast) {
            node = null;
        }
        if(node != null){
            node.left = preOrderDelete(node.left , depth + 1 , keyOfLast);
            node.right = preOrderDelete(node.right , depth + 1 , keyOfLast);
        }
        return node;
    }

    /**
     * This method fixes the heaps order relation after deletion operation
     * @return - returns the root lastly
     */
    private Node<E> fixHeap(){
        E holderE;
        int holderK;
        int depth = 0;
        if (root != null){
            Node<E> temp = root;
            while(depth < maxDepth){
                if(temp.left == null && temp.right == null) break;
                if (temp.right == null || temp.left.key < temp.right.key){
                    holderE = temp.data;
                    temp.data = temp.left.data;
                    temp.left.data = holderE;
                    holderK = temp.key;
                    temp.key = temp.left.key;
                    temp.left.key = holderK;
                    temp = temp.left;
                }
                else if (temp.left.key > temp.right.key){
                    holderE = temp.data;
                    temp.data = temp.right.data;
                    temp.right.data = holderE;
                    holderK = temp.key;
                    temp.key = temp.right.key;
                    temp.right.key = holderK;
                    temp = temp.right;
                }
                depth++;
            }
            return root;
        }
        else{
            return null;
        }
    }

    /**
     * This method is used for incrementing an elements' priority value
     * @param data - indicates data of the element
     * @param keyValue - indicates key value of the element
     */
    public void incrementPriority(E data , int keyValue){

        root = preOrderFind2(root , data , keyValue-1);
        root = preOrderFind1(root , keyValue);
        if (!findBool){
            System.out.println("There is no such data in heap!");
            return;
        }
        else if(keyValue == 0) {
            System.out.println("This is max priority!");
            return;
        }
        else root = preOrderFind2(root , data , keyValue-1); //roota cevrilebilir
    }

    /**
     * This method is used for incrementing the key value of specified element and called from incrementPriority
     * @param node - indicates subTrees
     * @param keyValue - indicates the key value of searching element
     * @return - Node<E> returns root lastly
     */
    private Node<E> preOrderFind1(Node<E> node , int keyValue) {

        if (node != null && node.key == keyValue) {
            findBool = true;
            node.data = tempData;
            return node;
        }
        if(node != null){
            node.left = preOrderFind1(node.left , keyValue);
            node.right = preOrderFind1(node.right , keyValue);
        }
        return node;
    }

    /**
     * This method is used for decrementing the key value of second element and called from incrementPriority
     * @param node - indicates subTrees
     * @param keyValue - indicates the key value of searching element
     * @param data - indicates the data of searching element
     * @return - Node<E> returns root lastly
     */
    private Node<E> preOrderFind2(Node<E> node , E data , int keyValue) {

        if (node != null && node.key == keyValue) {
            tempData = node.data;
            node.data = data;
            return node;
        }
        if(node != null){
            node.left = preOrderFind2(node.left , data , keyValue);
            node.right = preOrderFind2(node.right , data , keyValue);
        }
        return node;
    }

    /**
     * This method is one of the additional method of our BinaryHeap and uses for merging two heap structure
     * @param other - indicates the other BinaryHeap which will be added base heap
     * @return - new BinaryHeap structure
     */
    public BinaryHeap<E> mergeOperation(BinaryHeap<E> other){
        int counter = 0;
        while(other.keyValue+1 > counter){

            other.preOrderTraverse(other.root , 0);
            other.deleteReturn = other.root.data;
            other.root.data = other.lastNode.data;
            other.root.key = other.lastNode.key;
            other.root = other.preOrderDelete(other.root , 0 , (other.lastNode.key));
            other.root = other.fixHeap();

            root = this.offer(root, other.deleteReturn) ;
            counter++;
        }
        return this;
    }







}
