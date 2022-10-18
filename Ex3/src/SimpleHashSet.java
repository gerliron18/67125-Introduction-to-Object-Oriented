public abstract class SimpleHashSet implements SimpleSet {

    /**
     * Initial class instances.
     */
    protected static int INITIAL_CAPACITY = 16;
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;
    private static final int MULTIPLICATION_FACTOR = 2;
    private static final int MIN_TABLE_SIZE = 1;
    private static final int DELETION = 1;

    /**
     * The default initial capacity of the hash table.
     */
    protected int capacity;

    /**
     * The upper load factor of the hash table.
     */
    protected float upperLoadFactor;

    /**
     * The lower load factor of the hash table.
     */
    protected float lowerLoadFactor;

    /**
     *
     */
    protected int capacityMinusOne;

    /**
     * A flag that save the current size of the hash table.
     */
    private int sizeCalc = 0;

    /*----=  Constructors  =-----*/

    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    public SimpleHashSet() {
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = capacity - DELETION ;
        upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = capacity - DELETION ;
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();


    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index - the index before clamping
     * @return an index properly clamped
     */
    protected int clamp(int index){
        return index&capacityMinusOne;
    }

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return lowerLoadFactor;
    }


    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }


    @Override
    public int size() {
        return sizeCalc;
    }

    /**
     * Generates an updated hash table with an updated capacity
     * @param capacity - the capacity we would like the updating hash table to have.
     */
    protected abstract void resizeHashTable(int capacity);


    /**
     * An abstract method for adding a value to the hash table that
     * will be implement specifically at inherited classes
     * @param value - the value we want to add to the hash table.
     */
    protected abstract void specificAddition(String value);


    /**
     * An abstract method for deleting a value from the hash table that
     * will be implement specifically at inherited classes
     * @param value - the value we want to delete from the hash table.
     */
    protected abstract void specificDeletion(String value);


    /**
     *
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (contains(newValue)) {
            return false;
        } else {
            sizeCalc++;
            if ((float) sizeCalc / capacity > upperLoadFactor) {
                capacity = capacity * MULTIPLICATION_FACTOR;
                capacityMinusOne = capacity - DELETION ;
                resizeHashTable(capacity);
            }
            specificAddition(newValue);
            return true;
        }
    }


    /**
     *
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        if (contains(toDelete)){
            specificDeletion(toDelete);
            sizeCalc--;
            if ((float)sizeCalc/capacity < lowerLoadFactor){
                capacity = Math.max((capacity / MULTIPLICATION_FACTOR),MIN_TABLE_SIZE);
                capacityMinusOne = capacity - DELETION ;
                resizeHashTable(capacity);
            }
            return true;
        }else{
            return false;
        }
    }
}
