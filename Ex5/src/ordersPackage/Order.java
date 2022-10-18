package ordersPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * An interface for an Order object which orders files according to given parameters.
 */
public interface Order {

    /**
     * Method that gets an ArrayList of files and returns the files in an order according to given parameters.
     *
     * @param fileArrayList files to be ordered
     * @return An ArrayList of files in the correct order
     */
    public ArrayList<File> order(ArrayList<File> fileArrayList);

    /**
     * Method that compares two files according to a given parameter
     *
     * @param firstFile  first file to compare
     * @param secondFile second file to compare
     * @return An int representing which file comes first according to a specific order
     */
    public int compare(File firstFile, File secondFile);

    /**
     * Prints an error message.
     */
    public void printError();
}
