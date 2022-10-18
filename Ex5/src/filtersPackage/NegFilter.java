package filtersPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * A specific type of filter decorator - returns all the files that do NOT pass the filter.
 */
public class NegFilter extends FilterDecorator {

    private static final int EXPECTED_NUM_OF_PARAMS = 0;

    /**
     * Constructor for a negative filter
     *
     * @param negFilter the filter we want to add make negative.
     */
    NegFilter(Filter negFilter) {
        super(negFilter);
    }


    /**
     * A method that does the filtering of a given file array, by applying the isPass method an each one of
     * the files in the array, and returns the files that DON'T pass the filter.
     *
     * @param fileArrayList the ArrayList to be filed.
     * @return an array list with all the files that didn't pass the filter.
     */
    @Override
    public ArrayList<File> doFilter(ArrayList<File> fileArrayList) {

        ArrayList<File> filteredFiles = new ArrayList<>();

        for (File file : fileArrayList) {
            if (!isPass(file)) {
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    @Override
    public int getNumOfParams() {
        return EXPECTED_NUM_OF_PARAMS;
    }
}
