package filtersPackage;

import filesprocessing.ParseData;
import filesprocessing.TypeOneExceptions;
import filtersPackage.permissionFilters.Executable;
import filtersPackage.permissionFilters.Hidden;
import filtersPackage.permissionFilters.Writable;
import filtersPackage.sizeFilters.BetweenFilter;
import filtersPackage.sizeFilters.GreaterThan;
import filtersPackage.sizeFilters.SmallerThan;
import filtersPackage.textFilters.Contains;
import filtersPackage.textFilters.Prefix;
import filtersPackage.textFilters.Suffix;
import filtersPackage.textFilters.file;

/**
 * A factory that creates filter items according the given parameters (and decorators, if applicable). If
 * there is an error with the parameters, a default filter is returned
 */
public class FilterFactory {

    /**
     * Returns the specific type of filter according to the given params.
     *
     * @param filterType The name of the filter, which represents the type of filter is wanted
     * @param neg        a boolean parameters that marks if there is a negative decorator in the
     *                   call for creating the filter.
     * @param param      A string that can contain any number of params for the filters
     * @return A filter object.
     */
    public static Filter getFilter(String filterType, boolean neg, String... param) {

        //Line number with the instructions to create the filter.
        String lineCounter = Integer.toString(ParseData.lineCounter);

        //Initiate a new filter object.
        Filter newFilter;

        try {
            switch (filterType) {
                case "greater_than":
                    newFilter = new GreaterThan(param[0]);
                    break;
                case "smaller_than":
                    newFilter = new SmallerThan(param[0]);
                    break;
                case "between":
                    newFilter = new BetweenFilter(param[0], param[1]);
                    break;
                case "file":
                    newFilter = new file(param[0]);
                    break;
                case "contains":
                    newFilter = new Contains(param[0]);
                    break;
                case "prefix":
                    newFilter = new Prefix(param[0]);
                    break;
                case "suffix":
                    newFilter = new Suffix(param[0]);
                    break;
                case "writable":
                    newFilter = new Writable(param[0]);
                    break;
                case "executable":
                    newFilter = new Executable(param[0]);
                    break;
                case "hidden":
                    newFilter = new Hidden(param[0]);
                    break;
                case "all":
                    newFilter = new GeneralFilter();
                    break;
                default:
                    throw new TypeOneExceptions(lineCounter);
            }

            if ((!neg && newFilter.getNumOfParams() != param.length) ||
                    (neg && newFilter.getNumOfParams() != param.length - 1)) {
                throw new TypeOneExceptions(lineCounter);
            }

            //If the filter was sent with a "NOT" decorator.
            if (neg) {
                return new NegFilter(newFilter);
            }

        } catch (TypeOneExceptions typeOneExceptions) {
            newFilter = new GeneralFilter();
            ((GeneralFilter) newFilter).addError(typeOneExceptions);
            return newFilter;

        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            newFilter = new GeneralFilter();
            ((GeneralFilter) newFilter).addError(new TypeOneExceptions(lineCounter));
            return newFilter;
        }
        return newFilter;
    }

}
