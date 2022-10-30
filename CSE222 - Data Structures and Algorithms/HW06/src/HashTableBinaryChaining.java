package hw6;
/** Implements the chaining technique for hashing.
 * However, it uses binary search trees to chain items mapped on the same table slot.
 *   @author Ali Kaya
 * */
public class HashTableBinaryChaining<K , V> implements KWHashMap<K , V>{

    /** The table */
    private BinarySearchTree< Entry<K , V> >[] table;
    /** The number of keys */
    private int numKeys;
    /** The start capacity */
    private static final int START_CAPACITY = 11;
    /** The maximum load factor */
    private static final int LOAD_THRESHOLD = 3;

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K , V> implements Comparable<Entry<K, V>> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key , V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        public K getKey(){
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        public V getValue(){
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val){
            V oldValue = value;
            value = val;
            return oldValue;
        }

        /**
         * Overridden compareTo method for binary search tree comparison
         * @param other - other key-value pairs
         * @return - -1 if other's key is greater than our key
         *            0 if other's key and our key are equal to each other
         *            1 if other's key is less than our key
         */
        @Override
        public int compareTo(Entry<K, V> other) {
            String temp1 = this.toString();
            String temp2 = other.toString();
            return temp1.compareTo(temp2);
        }

        /**
         * Converts key in string form
         * @return - String - key in string form
         */
        public String toString()
        {
            return key.toString();
        }
    }

    /**
     * This constructor initiates our Hash table with start capacity
     */
    public HashTableBinaryChaining(){
        table = new BinarySearchTree[START_CAPACITY];
        numKeys = 0;
    }

    /**
     * Method get for class HashTableBinaryChaining.
     * @param key The key being sought
     * @return The value associated with this key if found;
     * otherwise, null
     */
    @Override
    public V get(Object key) {

        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;
        }

        if (table[index] == null)
            return null;

        Entry<K , V> temp = new Entry<K , V>((K) key, (V) "0");
        Entry<K , V> result =  table[index].find(temp);
        if (result != null) return result.value;
        else return null;
    }

    /**
     * put method is used for inserting new elements to the table which contains binary search trees.
     * post: This key-value pair is inserted in the
     * table and numKeys is incremented. If the key is already
     * in the table, its value is changed to the argument
     * value and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if
     * found; otherwise, null
     */
    @Override
    public V put(K key, V value) {

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            table[index] = new BinarySearchTree<Entry<K , V>>();
        }
        Entry<K , V> temp = new Entry<K , V>(key, (V) "0");
        Entry<K , V> result =  table[index].find(temp);
        if (result != null) {
            V oldValue = table[index].find(temp).value;
            table[index].find(temp).setValue(value);
            return oldValue;
        }

        table[index].add(new Entry<K , V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)){
            rehash();
        }
        return null;
    }

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * post: the size of table is doubled and is an
     * odd integer. Each non-deleted entry from the original
     * table is reinserted into the expanded table.
     * The value of numKeys is reset to the number of items
     * actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {

        BinarySearchTree< Entry < K, V >>[] oldTable = table;
        table = new BinarySearchTree[2 * oldTable.length + 1];

        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                preOrder(oldTable[i].root);
            }
        }
    }

    /**
     * This preOrder method is used for traverse the binary search tree for reinsert it into table
     * @param node -root of binary search tree
     */
    private void preOrder(BinarySearchTree.Node<Entry<K,V>> node) {
        if (node == null) {
            return;
        }
        put(node.data.key , node.data.value); // process node
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Method remove for class HashTableBinaryChaining
     * @param key The key of item being removed
     * @return - returns oldValue if specified key is in table, otherwise returns null.
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; // Key not in table

        Entry<K , V> temp = new Entry<K , V>((K)key , (V)"0");
        Entry<K , V> result =  table[index].delete(temp);
        numKeys--;
        if (result != null) {
            return result.value;
        }
        return null; // Key not in table
    }

    /** Returns true if empty */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }
    /** Returns the number of entries in the map */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * This toString method is used to print the view of the table to the screen.
     * @return - String format of our table
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------\n");
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                sb.append("[empty table slot]\n");
                sb.append("-----------------------\n");
            }
            else {
                sb.append(myToString(table[i].root));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * This method is used to perform toString method for each subtree
     * @param node indicates roots of subtrees
     * @return
     */
    public String myToString(BinarySearchTree.Node<Entry<K,V>> node) {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(node, 1, sb);
        sb.append("-----------------------");
        return sb.toString();
    }

    /** Perform a preorder  for our binary search tree.
     @param node The local root
     @param depth The depth
     @param sb The string buffer to save the output
     */
    private void preOrderTraverse(BinarySearchTree.Node<Entry<K,V>> node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("    ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.data.getValue());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }
}
