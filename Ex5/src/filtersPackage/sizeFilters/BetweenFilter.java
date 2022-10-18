package filtersPackage.sizeFilters;

import filesprocessing.TypeOneExceptions;

import java.io.File;

/**
 * A filter that filters files according to whether or not the size of the files are between two values.
 */
public class BetweenFilter extends SizeFilter {

    private static final int EXPECTED_NUM_OF_PARAMS = 2;

    /**
     * Constructor for BetweenFilter
     *
     * @param value1 the size that a file must be greater than or equal in order to pass the filter.
     * @param value2 the size that a file must be smaller than or equal in order to pass the filter.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public BetweenFilter(String value1, String value2) throws TypeOneExceptions {
        super(value1, value2);

    }

    /**
     * Checks if the size of a file is greater than the the lower threshold and smaller than the upper
     * threshold.
     *
     * @param file file being checked.
     * @return true - if file is in the legal range, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return (file.length() >= value1 * CONVERT_B_TO_KB) && (file.length() <= value2 * CONVERT_B_TO_KB);
    }

    @Override
    public int getNumOfParams() {
        return EXPECTED_NUM_OF_PARAMS;
    }
}
