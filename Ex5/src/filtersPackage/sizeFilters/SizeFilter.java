package filtersPackage.sizeFilters;

import filesprocessing.TypeOneExceptions;
import filtersPackage.GeneralFilter;

/**
 * A class representing filters that filter according to the the size of files.
 */
public abstract class SizeFilter extends GeneralFilter {

    /**
     * Conversion factor from bytes to kilobytes.
     */
    final int CONVERT_B_TO_KB = 1024;

    /**
     * Possible number of parameters the filter can get.
     */
    static final int POSSIBLE_NUM_OF_PARAM = 1;


    /**
     * The first number (size of file) by which the files will be filtered.
     */
    protected double value1;

    /**
     * The second number (size of file) by which the files will be filtered, if applicable.
     */
    protected double value2;

    /**
     * Constructor for filters that filter according the the size of a file using only one parameter.
     *
     * @param value1 The first number (size of file) by which the files will be filtered.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public SizeFilter(String value1) throws TypeOneExceptions {
        this.value1 = Double.parseDouble(value1);

        if (!isNumNonNegative(this.value1)) {
            throw new TypeOneExceptions(lineCounter);
        }
    }

    /**
     * Constructor for filters that filter according the the size of a file using two parameters.
     *
     * @param value1 The first number (size of file) by which the files will be filtered.
     * @param value2 The second number (size of file) by which the files will be filtered.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public SizeFilter(String value1, String value2) throws TypeOneExceptions {
        this.value1 = Double.parseDouble(value1);
        this.value2 = Double.parseDouble(value2);

        if (!validateTwoParams()) {
            throw new TypeOneExceptions(lineCounter);
        }
    }


    /**
     * Checks if the input value is not a negative number.
     *
     * @param num number being checked.
     * @return true - if number is not negative, false - otherwise.
     */
    private boolean isNumNonNegative(double num) {
        return num >= 0;
    }

    /**
     * Checks if the parameters given for a filter that receives two values is legal.
     *
     * @return true - if values are legal (both value are not negative, and first value smaller or equal to
     * second value). False - otherwise.
     */
    public boolean validateTwoParams() {
        return isNumNonNegative(value1) && isNumNonNegative(value2) && (value1 <= value2);
    }

    @Override
    public int getNumOfParams() {
        return POSSIBLE_NUM_OF_PARAM;
    }
}
