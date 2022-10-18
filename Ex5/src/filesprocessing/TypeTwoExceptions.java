package filesprocessing;

/**
 * Exception that catch an error due to problems related to the source directory, command file (including
 * bad format of the file) and IO problems. These exceptions cause the program to stop running.
 */
public class TypeTwoExceptions extends Exception {

    /**
     * The start of every Type Two error message.
     */
    private static final String ERROR_MSG = "ERROR: ";

    /**
     * Message that is printed when an illegal number of parameters were entered into the command line.
     */
    static final String ILLEGAL_PARAM_NUM_MSG = "Illegal number of parameters. Enter only two parameters: " +
            "Source Directory and Commands File.";

    /**
     * Message that is printed when a problem arises due to an IO error.
     */
    private static final String IO_PROBLEM_MSG = "Problem accessing Commands File.";

    /**
     * Message that is printed when there is a bad format in the command file.
     */
    static final String BAD_FORMAT_MSG = "Bad format in Command File (i.e., no ORDER sub-section).";

    /**
     * Message that is printed when there are no files in the source directory.
     */
    static final String NO_SRC_FILE = "No files in source directory.";

    /**
     * A method that prints an error message not related to IO problem.
     *
     * @param message The type of message that should be printed.
     */
    public TypeTwoExceptions(String message) {
        System.err.println(ERROR_MSG + message);

    }

    /**
     * A method that prints an error message related to IO problem.
     */
    public static void printIOProblem() {
        System.err.println(ERROR_MSG + IO_PROBLEM_MSG);
    }
}
