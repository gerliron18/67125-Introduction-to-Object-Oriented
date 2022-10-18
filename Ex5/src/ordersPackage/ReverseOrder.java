package ordersPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A specific type of order decorator - returns all the files that in a reverse order.
 */
public class ReverseOrder extends OrderDecorator {
    /**
     * Constructor for a reverse order
     *
     * @param decoratedOrder order to be reversed
     */
    ReverseOrder(Order decoratedOrder) {
        super(decoratedOrder);
    }

    /**
     * Method that gets an ArrayList of files and returns the files in a REVERSE order in regards to the
     * given parameters.
     *
     * @param fileArrayList files to be ordered
     * @return An ArrayList of files in the reversed order
     */
    @Override
    public ArrayList<File> order(ArrayList<File> fileArrayList) {
        fileArrayList.sort(this::compare);
        Collections.reverse(fileArrayList);
        return fileArrayList;
    }

}
