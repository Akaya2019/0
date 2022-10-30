package hw6;
/** Implements a hashing technique that is a combination of the double hashing and coalesced hashing techniques.
 *   @author Ali Kaya
 * */
public class HashTableCombine<K , V> implements KWHashMap<K, V>{

    /** The table */
    private Entry<K , V>[] table;
    /** The start capacity */
    private static final int START_CAPACITY = 10;
    /** The maximum load factor */
    private final double LOAD_THRESHOLD = 0.75;
    /** The number of keys */
    private int numKeys;
    /** The number of deleted keys */
    private int numDeletes;
    private final Entry<K , V> DELETED = new Entry<K ,V>(null ,null);

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K , V>{

        /** The Key*/
        private K key;          //also data itself
        /** The value*/
        private V value;
        /** The link for next of this node*/
        private Entry<K , V> next;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key , V value){
            this.key = key;
            this.value = value;
            next = null;
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
         * Sets the value be DELETED.
         * @return The old value
         */
        public V setDel(){
            V oldValue = value;
            value = null;
            key = null;
            return oldValue;
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
    public HashTableCombine(){
        table = new Entry[START_CAPACITY];
        numKeys = 0;
        numDeletes = 0;
    }

    /**
     * Method get for class HashTableCombine.
     * @param key The key being sought
     * @return The value associated with this key if found;
     * otherwise, null
     */
    @Override
    public V get(Object key) {

        int Prime_number = Prime_number();
        int Hash1 = key.hashCode() % table.length;
        int Hash2 = Prime_number - (key.hashCode() % Prime_number);
        int index = ( Hash1 + (Hash2) ) % table.length;

        if (index < 0)
            index += table.length;

        if (table[index] == null){
            return null;
        }

        int i = 2;
        while((table[index] != null) && (!key.equals(table[index].key))){
            index = ( Hash1 + (i*Hash2) ) % table.length;
            if (index < 0)
                index += table.length;
            i++;
        }
        if (table[index] != null){
            return (V) table[index].key;
        }
        else
            return null;
    }

    /**
     * This method is used to finding specified the greatest prime at that time
     * @return -int specified prime number
     */
    private int Prime_number() {

        int largestPrime = (int)(table.length * (0.8));
        largestPrime--;

        boolean isItPrime;
        if(largestPrime <= 1) {
            isItPrime = false;
        }
        else {
            while (largestPrime>1){
                if(largestPrime%2 == 0){
                    isItPrime = false;
                    largestPrime--;
                }
                else{
                    isItPrime = true;
                    for (int i = 3; i<= largestPrime/3; i+=2)
                    {
                        if ((largestPrime % i) == 0)
                        {
                            isItPrime = false;
                            break;
                        }
                    }
                    if (isItPrime) break;
                    largestPrime -= 2;
                }

            }
        }
        return (int)largestPrime;
    }

    /**
     * put method is used for inserting new elements to the table with double hashing technique.
     * This method also links two nodes that have same hash func. consequence
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

        int i = 1;
        int Prime_number = Prime_number();
        int Hash1 = key.hashCode() % table.length;
        int Hash2 = Prime_number - (key.hashCode() % Prime_number);
        int index;

        while(true){

            index = (Hash1 + (i*Hash2)) % table.length;
            if (index < 0)
                index += table.length;

            if (table[index] == null){

                table[index] = new Entry<K , V>(key, value);
                numKeys++;
                if(i != 1) {
                    int tempIndex = (Hash1 + ((i-1)*Hash2)) % table.length;
                    if (tempIndex < 0) tempIndex += table.length;
                    table[tempIndex].next = table[index] ;
                }

                double loadFactor = (double) (numKeys+numDeletes)/ table.length;
                if (loadFactor > LOAD_THRESHOLD)
                    rehash();
                return null;
            }
            else if(key.equals(table[index].key)){

                V oldVal = (V) table[index].key;
                table[index].key = key;
                return oldVal;
            }
            else
                i++;
        }
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
        Entry<K , V>[] oldTable = table;
        table = new Entry[2 * oldTable.length + 1];
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {

            if((oldTable[i] != null) && (oldTable[i].key != null)){
                put(oldTable[i].key , oldTable[i].value);
            }
        }
    }

    /** Remove the item with a given key value
     *  @param key The key to be removed
     *  @return The value associated with this key, or null
     *  if the key is not in the table.
     */
    @Override
    public V remove(Object key) {
        int i = 1;
        int Prime_number = Prime_number();
        int Hash1 = key.hashCode() % table.length;
        int Hash2 = Prime_number - (key.hashCode() % Prime_number);
        int index;

        while(true){
            index = (Hash1 + (i*Hash2)) % table.length;
            if (index < 0)
                index += table.length;

            if (table[index] == null){
                return null;
            }
            else if(key.equals(table[index].key)){

                V oldValue = (V) table[index].key;
                if(table[index].next == null){
                    table[index] = DELETED;
                }
                else{
                    Entry<K , V> cur = table[index];
                    Entry<K , V> prev = table[index];
                    while(cur.next != null){
                        cur.key = cur.next.key;
                        cur.value = cur.next.value;
                        cur = cur.next;

                        prev.next = cur.next;
                        prev = cur;

                        if(cur.next==null) {
                            prev.setDel();
                        }
                    }
                }
                numKeys--;
                numDeletes++;
                return oldValue;
            }
            else
                i++;
        }

    }

    /** Returns true if table is empty */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /** Returns the number of entries in the map */
    @Override
    public int size() {
        return numKeys;
    }
    public int capacity(){
        return table.length;
    }

    /**
     * This toString method is used to print the view of the table to the screen.
     * @return - String format of our table
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(String.format("%1s%1d%-4s","[",i,"]"));

            if(table[i] != null){
                if(table[i].key != null) sb.append(String.format("%-2s",table[i].key));
                else sb.append(String.format("%-2s","DL"));
                sb.append("   ");
                if(table[i].next != null){
                    sb.append(table[i].next);
                    sb.append("\n");
                }
                else
                    sb.append("null\n");
            }
            else {
                sb.append("     ");
                sb.append("null\n");
            }
        }
        return sb.toString();
    }
}
