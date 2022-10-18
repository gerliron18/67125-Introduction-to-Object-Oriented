package filtersPackage.permissionFilters;

import filesprocessing.TypeOneExceptions;
import filtersPackage.GeneralFilter;

/**
 * A class representing filters that filter according to the permissions that a file has. For example( if
 * the file is writable, executable, etc.)
 */
public abstract class PermissionFilter extends GeneralFilter {

    /**
     * A string that represents a legal parameter in a general filter, marking that the specific permission
     * type should be applied when filtering the files.
     */
    private static final String LEGAL_YES = "YES";

    /**
     * A string that represents an illegal parameter in a general filter,
     * marking that the specific permission type should NOT be applied when filtering the files.
     */
    private static final String LEGAL_NO = "NO";

    /**
     * The expected number of parameters that a permission filter can receive.
     */
    private static final int EXPECTED_NUM_OF_PARAM = 1;

    /**
     * A boolean representing if the input was a "YES" (true) or a "NO" (false).
     */
    boolean param;

    /**
     * Constructor for permission filters. Receives the parameters from the command file and saves them as
     * a boolean value.
     *
     * @param param the input to be turned into a boolean (either "YES" or "NO"), marking if we are
     *              filtering by files that have the given permission or don't have the given permission.
     * @throws TypeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                           names, and illegal values for the filters.
     */
    public PermissionFilter(String param) throws TypeOneExceptions {
        switch (param) {
            case LEGAL_YES:
                this.param = true;
                break;
            case LEGAL_NO:
                this.param = false;
                break;
            default:
                throw new TypeOneExceptions(lineCounter);
        }

    }

    @Override
    public int getNumOfParams() {
        return EXPECTED_NUM_OF_PARAM;
    }
}
