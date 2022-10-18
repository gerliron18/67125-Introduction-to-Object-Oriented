package filesprocessing;

import filtersPackage.Filter;
import ordersPackage.Order;

import java.io.File;
import java.util.ArrayList;

/**
 * A class representing a Section object. A section object holds two fields - a filter and an order.
 */
public class Section {

    /**
     * A filter object.
     */
    private Filter filter;

    /**
     * An order object.
     */
    private Order order;

    /**
     * Section Constructor
     *
     * @param filter a filter object.
     * @param order  an order object.
     */
    Section(Filter filter, Order order) {
        this.filter = filter;
        this.order = order;

    }

    /**
     * Gets an ArrayList of files and runs the filter and the order instructions on the files.
     *
     * @param files A list of files to be filtered and ordered.
     * @return An ArrayList of all the filtered and sorted files.
     */
    public ArrayList<File> runSection(ArrayList<File> files) {
        filter.printError();
        order.printError();
        return order.order(filter.doFilter(files));

    }
}
