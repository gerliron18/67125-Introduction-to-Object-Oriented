package filtersPackage;

import filesprocessing.TypeOneExceptions;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that allows to add decorators to a filter object
 */

abstract class FilterDecorator implements Filter {

    /**
     * A decorated filter
     */
    private Filter decoratedFilter;

    /**
     * A Type One Exception to be thrown in there is a problem generating the filter.
     */
    private TypeOneExceptions typeOneExceptions;

    /**
     * Constructor for a decorated filter
     *
     * @param decoratedFilter the filter to be decorated
     */
    FilterDecorator(Filter decoratedFilter) {
        this.decoratedFilter = decoratedFilter;

    }

    @Override
    public ArrayList<File> doFilter(ArrayList<File> fileArrayList) {
        return decoratedFilter.doFilter(fileArrayList);
    }

    @Override
    public boolean isPass(File file) {
        return decoratedFilter.isPass(file);
    }

    /**
     * Adds a Type One Exception to a filter object.
     *
     * @param typeOneExceptions An exceptions related to a bad filter/order names, bad parameter
     *                          names, and illegal values for the filters.
     */
    public void addError(TypeOneExceptions typeOneExceptions) {
        this.typeOneExceptions = typeOneExceptions;

    }

    /**
     * A method that prints an error message.
     */
    public void printError() {
        if (this.typeOneExceptions != null) {
            this.typeOneExceptions.printErrorMSG();
        } else {
            decoratedFilter.printError();
        }
    }
}
