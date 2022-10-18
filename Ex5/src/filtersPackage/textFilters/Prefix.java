package filtersPackage.textFilters;

import java.io.File;

/**
 * A filter object that filters file according to whether or not a given String of characters is the prefix
 * of the file name.
 */

public class Prefix extends TextFilter {

    /**
     * Constructor for a Prefix filter
     *
     * @param val the String we are looking for in the prefix of the file name.
     */
    public Prefix(String val) {
        super(val);
    }

    /**
     * Checks if a file name starts with the given string.
     *
     * @param file file being checked.
     * @return true - if the file name starts with the given String, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return file.getName().startsWith(val);
    }

}
