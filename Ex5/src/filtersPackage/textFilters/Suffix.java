package filtersPackage.textFilters;

import java.io.File;

/**
 * A filter object that filters file according to whether or not a given String of characters is the suffix
 * of the file name.
 */
public class Suffix extends TextFilter {


    /**
     * Constructor for a Suffix filter
     *
     * @param val the String we are looking for in the suffix of the file name.
     */
    public Suffix(String val) {
        super(val);
    }


    /**
     * Checks if a file name ends with the given string.
     *
     * @param file file being checked.
     * @return true - if the file name ends with the given String, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return file.getName().endsWith(val);
    }

}
