package ordersPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that represents an order object. This order sorts files according to the file size.
 */

public class SizeOrder extends AbsOrder {

    /**
     * Function that compares two files according to their size
     *
     * @param firstFile  first file to compare
     * @param secondFile second file to compare
     * @return if file size equals - return abs order for the file, else - return the size difference of
     * the files.
     */
    public int compare(File firstFile, File secondFile) {
        long order = firstFile.length() - secondFile.length();
        if (order == 0) {
            return super.compare(firstFile, secondFile);
        }
        return (int) order;
    }

    /**
     * Sort files by file size, going from smallest to largest.
     *
     * @param fileArrayList An array of files need to be ordered.
     * @return An ordered by size files array.
     */
    @Override
    public ArrayList<File> order(ArrayList<File> fileArrayList) {
        fileArrayList.sort(this::compare);
        return fileArrayList;
    }

}
