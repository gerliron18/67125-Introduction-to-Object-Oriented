package filtersPackage.textFilters;

import java.io.File;


/**
 * A filter object that filters file according to whether or not a given String of characters is the name
 * of the file name.
 */
public class file extends TextFilter {

    /**
     * Constructor for file filter
     *
     * @param val the String we are looking for in the file name
     */
    public file(String val) {
        super(val);
    }

    @Override
    public boolean isPass(File file) {
        return file.getName().equals(val);
    }

}
