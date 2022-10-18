package filtersPackage.textFilters;

import java.io.File;

/**
 * A filter object that filters file according to whether or not a given String of characters is contained in
 * the file name.
 */
public class Contains extends TextFilter {

    /**
     * Constructor for Contains filter
     *
     * @param val the String we are looking for in the file name
     */
    public Contains(String val) {
        super(val);
    }

    /**
     * Checks if a file contains a given string.
     *
     * @param file file being checked.
     * @return true - if the file name contains the given String, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return file.getName().contains(val);
    }

}
