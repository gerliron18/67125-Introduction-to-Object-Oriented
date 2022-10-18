package filesprocessing;

/**
 * A class representing a TypeOneException. These exceptions include bad filter/order names, bad parameter
 * names, and illegal values for the filters.
 */
public class TypeOneExceptions extends Exception {

    /**
     * The line number in which the error occurred.
     */
    private String lineNum;

    /**
     * Constructor for the exception
     *
     * @param lineNum the line number in which the error occurred;
     */
    public TypeOneExceptions(String lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * Prints the error message along with the line in which the error occurred.
     */
    public void printErrorMSG() {
        /*
      The message to be printed when a Type One Exception is thrown.
     */
        String ERROR_MSG = "Warning in line ";
        System.err.println(ERROR_MSG + lineNum);
    }
}
