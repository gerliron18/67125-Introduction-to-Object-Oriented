import java.util.LinkedList;

public class LinkedListWrapper {

    /**
     * Initial class instances.
     */

    /**
     * The bucket of strings at one cell of the hash table.
     */
    private LinkedList<String> bucket;

    /**
     * The array of strings contain all bucket values.
     */
    private String[] bucketValues;


    /*----=  Constructors  =-----*/

    /**
     * Constructs a new, empty cell bucket as a linked list.
     */
    public LinkedListWrapper() {
        bucket = new LinkedList<String>();
    }


    /*----=  Instance Methods  =-----*/

    /**
     * Add a specified element to the bucket.
     * @param newValue - the element we would like to add to the bucket
     */
    public void add(String newValue) {
        bucket.add(newValue);
    }

    /**
     * Look for a specified value in the bucket.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the bucket
     */
    public boolean contains(String searchVal) {
        for (String value: bucket){
            if (searchVal.equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the input element from the bucket.
     * @param toDelete Value to delete
     */
    public void delete(String toDelete) {
        bucket.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return bucket.size();
    }

    /**
     * Convert the bucket to array of strings.
     * @return an array of the string housed in the bucket
     */
    public String[] toArray(){
        bucketValues = bucket.toArray(new String[size()]);
        return bucketValues;
    }
}
