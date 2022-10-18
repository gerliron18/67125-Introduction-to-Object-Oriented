public class ClosedCubicle {

    /**
     * Initial class instances.
     */

    /**
     * The value saved at the cubicle.
     */
    private String value;

    /**
     * A flag if this cubicle had a value that was deleted from it.
     */
    private boolean drainded = false;


    /*----=  Constructors  =-----*/

    /**
     * Constructs a new, empty cubicle.
     */
    public ClosedCubicle() {
    }


    /*----=  Instance Methods  =-----*/

    /**
     * @return the value saved at the cubicle.
     */
    public String getValue(){
        return value;
    }

    /**
     * Set a value to be saved at the cubicle.
     * @param newValue - the value we want to save at the cubicle
     */
    public void setValue(String newValue){
        value = newValue;
    }

    /**
     * @return true if a value was deleted from the cubicle
     */
    public boolean isDrainded() {
        return drainded;
    }

    /**
     * Delete the saved value from the cubicle.
     */
    public void drainCubicle(){
        value = null;
        drainded = true;
    }
}
