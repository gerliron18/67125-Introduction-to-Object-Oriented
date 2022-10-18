public class ClosedHashSet extends SimpleHashSet {

    /**
     * Initial class instances.
     */
    private static final int MULTIPLICATION_FACTOR = 2;

    /**
     * The hash table.
     */
    private ClosedCubicle[] hashTable;

    /**
     * A flag that save the current size of the hash table.
     */
    private int sizeCalc = 0;


    /*----=  Constructors  =-----*/

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        hashTable = new ClosedCubicle[capacity];
        initializeHashTable();
    }

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new ClosedCubicle[capacity];
        initializeHashTable();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        super();
        hashTable = new ClosedCubicle[capacity];
        initializeHashTable();
        for (String value: data){
            add(value);
        }
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Initialize the closed hash table with closed cubicles as cells at the table.
     */
    private void initializeHashTable(){
        for (int index=0; index < capacity; index++){
            hashTable[index] = new ClosedCubicle();
        }
    }


    /**
     * Calculates the hash code index of a given string.
     * @param toHash - The string we want to find his hash index.
     * @return - The hash code of a string
     */
    private int calcHashCode(String toHash, int numAttempt){
        int index = toHash.hashCode();
        int factor = (numAttempt+(numAttempt*numAttempt))/MULTIPLICATION_FACTOR;
        index = clamp(index + factor);
        return index;
    }


    @Override
    protected void resizeHashTable(int capacity) {
        ClosedCubicle[] toSave = hashTable;
        hashTable = new ClosedCubicle[capacity];
        initializeHashTable();
        for (ClosedCubicle value: toSave){
            if (value.getValue() != null){
                specificAddition(value.getValue());
            }
        }
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int attempt = 0;
        while (attempt <= capacity){
            int index = calcHashCode(searchVal, attempt);
            if ((hashTable[index].getValue() != null) && (hashTable[index].getValue().equals(searchVal))){
                return true;
            }else{
                if ((hashTable[index].isDrainded()) || (hashTable[index].getValue() != null)){
                    attempt++;
                }else{
                    return false;
                }
            }
        }
        return false;
    }


    @Override
    protected void specificAddition(String value) {
        int attempt = 0;
        while (true){
            int index = calcHashCode(value,attempt);
            if (hashTable[index].getValue() == null){
                hashTable[index].setValue(value);
                break;
            }else{
                attempt++;
            }
        }
    }

    @Override
    protected void specificDeletion(String value) {
        int attempt = 0;
        while (true){
            int index = calcHashCode(value,attempt);
            if ((hashTable[index].getValue() != null) && (hashTable[index].getValue().equals(value))){
                hashTable[index].drainCubicle();
                break;
            }else{
                attempt++;
            }
        }
    }


    /**
     * @return The current capacity (number of cells) of the table.
     */
    @Override
    public int capacity() {
        return  capacity;
    }

}
