package ordersPackage;

import filesprocessing.ParseData;
import filesprocessing.TypeOneExceptions;

/**
 * A factory that creates order items according the given parameters (and decorators, if applicable). If
 * there is an error with the parameters, a default order is returned
 */
public class OrderFactory {
    /**
     * Returns the specific type of order according to the given params.
     *
     * @param OrderType the type of order to be created
     * @param reverse   a boolean value representing if the order created should be decorated with a reverse
     *                  decorator
     * @return an order object
     */
    public static Order getOrder(String OrderType, boolean reverse) {

        /*
          The line on which the instruction for creating the order in the command file. Used in case an
           exception is thrown.
         */
        String lineCounter = Integer.toString(ParseData.lineCounter);

        /*
          Initialize an order object.
         */
        Order order;

        try {
            switch (OrderType) {
                case "size":
                    order = new SizeOrder();
                    break;
                case "type":
                    order = new TypeOrder();
                    break;
                case "abs":
                    order = new AbsOrder();
                    break;
                default:
                    throw new TypeOneExceptions(lineCounter);
            }
            if (reverse) {
                return new ReverseOrder(order);
            }
        } catch (TypeOneExceptions e) {
            order = new AbsOrder();
            ((AbsOrder) order).addError(e);
            return order;
        }

        return order;
    }
}
