package filtersPackage.textFilters;

import filtersPackage.GeneralFilter;

/**
 * A class representing filters that filter files according to a String of character that appear in the
 * file name.
 */
public abstract class TextFilter extends GeneralFilter {

    /**
     * The expected number of parameters that a text filter can receive.
     */
    private static final int EXPECTED_NUM_OF_PARAM = 1;
    /**
     * The value that we are looking for in the file name
     */
    String val;

    /**
     * Constructor for TextFilters
     *
     * @param val the String we are looking for in the file name
     */
    public TextFilter(String val) {
        this.val = val;
    }

    @Override
    public int getNumOfParams() {
        return EXPECTED_NUM_OF_PARAM;
    }
}
