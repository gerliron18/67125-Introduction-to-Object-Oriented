package ordersPackage;

import filesprocessing.TypeOneExceptions;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that represents an abs order object. This order sorts the files by absolute name, going from
 * 'a' to 'z'.
 */
public class AbsOrder implements Order {

    /**
     * A Type One Exception to be thrown in there is a problem generating the order.
     */
    private TypeOneExceptions typeOneExceptions = null;

    /**
     * A method that orders the files by absolute name, going from 'a' to 'z'.
     *
     * @param fileArrayList An array of files that need to be ordered.
     * @return An array of files order according to their absolute name, going from 'a' to 'z'.
     */
    @Override
    public ArrayList<File> order(ArrayList<File> fileArrayList) {
        fileArrayList.sort(this::compare);
        return fileArrayList;
    }

    /**
     * Function that compares two files according to their absolute path
     *
     * @param firstFile  first file to compare
     * @param secondFile second file to compare
     * @return the value {@code 0} if the argument string is equal to
     * this string; a value less than {@code 0} if this string
     * is lexicographically less than the string argument; and a
     * value greater than {@code 0} if this string is
     * lexicographically greater than the string argument.
     */
    public int compare(File firstFile, File secondFile) {
        return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
    }

    /**
     * Adds a new Type One Exception to an order object.
     *
     * @param typeOneExceptions An exception thrown in the case of a bad filter/order names, bad parameter
     *                          names, and illegal values for the filters.
     */
    public void addError(TypeOneExceptions typeOneExceptions) {
        this.typeOneExceptions = typeOneExceptions;

    }

    /**
     * Prints the Type One error message.
     */
    public void printError() {
        if (this.typeOneExceptions != null) {
            this.typeOneExceptions.printErrorMSG();
        }
    }
}

