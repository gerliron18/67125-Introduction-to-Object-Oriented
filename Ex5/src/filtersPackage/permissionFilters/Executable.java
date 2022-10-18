package filtersPackage.permissionFilters;

import filesprocessing.TypeOneExceptions;

import java.io.File;

/**
 * A class representing a filter that filters files according to whether or not they are executable.
 */
public class Executable extends PermissionFilter {

    /**
     * Constructor for Executable filter
     *
     * @param param the input to be turned into a boolean (either "YES" or "NO"), marking if we are
     *              filtering by files that are executable or are not executable.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public Executable(String param) throws TypeOneExceptions {
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
        return file.canExecute() == param;
    }


}
