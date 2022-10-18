package ordersPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that represents an order object. This order sorts files according to the file type.
 */
public class TypeOrder extends AbsOrder {


    /**
     * Function that compares two files according to their type
     *
     * @param firstFile  first file to compare
     * @param secondFile second file to compare
     * @return if file type equals - return abs order for the file, else - return the value {@code 0} if the
     * argument string is equal to this string; a value less than {@code 0} if this string is
     * lexicographically less than the string argument; and a value greater than {@code 0} if this string is
     * lexicographically greater than the string argument.
     */
    @Override
    public int compare(File firstFile, File secondFile) {
        String valueOne = getFileExtension(firstFile);
        String valueTwo = getFileExtension(secondFile);
        if (valueOne.equals(valueTwo)) {
            return super.compare(firstFile, secondFile);
        } else {
            return valueOne.compareTo(valueTwo);
        }
    }


    /**
     * A helper method for the compare function (of the TypeOrder) by getting the extension of a file.
     *
     * @param file The file that whose extension we want to get.
     * @return The string extension of the given file.
     */
    private static String getFileExtension(File file) {
        if (file == null) {
            return "";
        }
        String name = file.getName();
        int index = name.lastIndexOf('.');
        return index > 0 ? name.substring(index + 1) : "";
    }

    /**
     * Sort files by file type, according to the file extension.
     *
     * @param fileArrayList An array of files need to be ordered.
     * @return An ordered by type files array
     */
    @Override
    public ArrayList<File> order(ArrayList<File> fileArrayList) {
        fileArrayList.sort(this::compare);
        return fileArrayList;
    }
}
