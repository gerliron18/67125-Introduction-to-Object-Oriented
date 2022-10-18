public class OpenHashSet extends SimpleHashSet {

    /**
     * Initial class instances.
     */

    /**
     * The hash table.
     */
    private LinkedListWrapper[] hashTable;


    /*----=  Constructors  =-----*/

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        hashTable = new LinkedListWrapper[capacity];
        initializeHashTable();
    }

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new LinkedListWrapper[capacity];
        initializeHashTable();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        hashTable = new LinkedListWrapper[capacity];
        initializeHashTable();
        for (String value: data){
            add(value);
        }
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Initialize the open hash table with linked list for every cell at the table.
     */
    private void initializeHashTable(){
        for (int index=0; index < capacity; index++){
            hashTable[index] = new LinkedListWrapper();
        }
    }


    /**
     * Calculates the hash code index of a given string.
     * @param toHash - The string we want to find his hash index.
     * @return - The hash code of a string
     */
    private int calcHashCode(String toHash){
        int index = toHash.hashCode();
        index = clamp(index);
        return index;
    }


    @Override
    protected void resizeHashTable(int capacity){
        LinkedListWrapper[] toSave = hashTable;
        hashTable = new LinkedListWrapper[capacity];
        initializeHashTable();
        for (LinkedListWrapper bucket: toSave){
            for (String value: bucket.toArray()){
                specificAddition(value);
            }
        }
    }


    @Override
    public boolean contains(String searchVal) {
        int index = calcHashCode(searchVal);
        if (hashTable[index].contains(searchVal)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void specificAddition(String value) {
        int index = calcHashCode(value);
        hashTable[index].add(value);
    }


    @Override
    protected void specificDeletion(String value) {
        int index = calcHashCode(value);
        hashTable[index].delete(value);
    }


    @Override
    public int capacity() {
        return capacity;
    }
}
