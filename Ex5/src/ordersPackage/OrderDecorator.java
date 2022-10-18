package ordersPackage;

import filesprocessing.TypeOneExceptions;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that allows to add decorators to an order object
 */

public abstract class OrderDecorator implements Order {

    /**
     * A decorated order
     */
    private Order decoratedOrder;

    /**
     * A Type One Exception to be thrown in there is a problem generating the order.
     */
    private TypeOneExceptions typeOneExceptions = null;

    /**
     * Constructor for a decorated order
     *
     * @param decoratedOrder order to be decorated
     */
    OrderDecorator(Order decoratedOrder) {
        this.decoratedOrder = decoratedOrder;

    }

    @Override
    public ArrayList<File> order(ArrayList<File> fileArrayList) {
        return decoratedOrder.order(fileArrayList);
    }

    @Override
    public int compare(File firstFile, File secondFile) {
        return decoratedOrder.compare(firstFile, secondFile);
    }

    /**
     * Adds a Type One Exception to an order object.
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
            decoratedOrder.printError();
        }
    }
}
