import java.util.TreeSet;

public class CollectionFacadeSet implements SimpleSet{

    /**
     * Initial class instances.
     */
    private java.util.Collection<String> collection;


    /*----=  Constructors  =-----*/

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collection = collection;
        removeDuplicates();
    }


    /*----=  Instance Methods  =-----*/


    /**
     * Remove all value duplications from a given set.
     */
    private void removeDuplicates(){
        java.util.Collection<java.lang.String> toSave = new TreeSet<String>(collection);
        collection.clear();
        collection.addAll(toSave);
    }
    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (!collection.contains(newValue)){
            collection.add(newValue);
            return true;
        } else{
            return false;
        }
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return collection.size();
    }
}
