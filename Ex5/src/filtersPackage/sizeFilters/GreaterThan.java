package filtersPackage.sizeFilters;

import filesprocessing.TypeOneExceptions;

import java.io.File;

/**
 * A filter that filters files according to whether or not the size of the files are greater than a given
 * value.
 */
public class GreaterThan extends SizeFilter {

    /**
     * The constructor for a GreaterThan filter
     *
     * @param value A number representing the size a file needs to be greater than, in order for the file to
     *              be returned (and not filtered out).
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public GreaterThan(String value) throws TypeOneExceptions {
        super(value);
    }

    /**
     * Checks if the size of a file is greater than the threshold.
     *
     * @param file file being checked.
     * @return true - if file is greater than the threshold, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return (file.length() > (value1 * CONVERT_B_TO_KB));
    }

    @Override
    public int getNumOfParams() {
        return POSSIBLE_NUM_OF_PARAM;
    }

}
