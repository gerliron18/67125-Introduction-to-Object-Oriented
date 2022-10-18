package filtersPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * An interface for a Filter object which can filter files according to given parameters.
 */
public interface Filter {
    /**
     * A method that checks if a given file passes the conditions of the filter.
     *
     * @param file the file being checked.
     * @return true - if the file meets that condition. False - otherwise.
     */
    public boolean isPass(File file);

    /**
     * A method that does the filtering of a given file array, by applying the isPass method an each one of
     * the files in the array.
     *
     * @param fileArrayList the ArrayList to be filed.
     * @return an array list with the filtered files.
     */
    public ArrayList<File> doFilter(ArrayList<File> fileArrayList);

    /**
     * @return the number of parameters of the filter.
     */
    public int getNumOfParams();

    /**
     * Prints a warning message if an error occurred.
     */
    public void printError();

}
