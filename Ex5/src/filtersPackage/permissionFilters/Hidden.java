package filtersPackage.permissionFilters;

import filesprocessing.TypeOneExceptions;

import java.io.File;

/**
 * A class representing a filter that filters files according to whether or not they are hidden.
 */
public class Hidden extends PermissionFilter {

    /**
     * Constructor for Hidden filter
     *
     * @param param the input to be turned into a boolean (either "YES" or "NO"), marking if we are
     *              filtering by files that are hidden or are not hidden.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public Hidden(String param) throws TypeOneExceptions {
        super(param);
    }

    /**
     * Checks if a file is executable or not.
     *
     * @param file file being checked.
     * @return true - if file is executable, false - otherwise.
     */
    @Override
    public boolean isPass(File file) {
        return file.isHidden() == param;
    }

}
