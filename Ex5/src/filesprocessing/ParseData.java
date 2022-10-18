package filesprocessing;

import filtersPackage.Filter;
import filtersPackage.FilterFactory;
import ordersPackage.Order;
import ordersPackage.OrderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class manages parsing the data given in the command file (Filter1 Array and Order Array operations).
 */
public class ParseData {

    /**
     * String representing a Filter object type.
     */
    public static final String FILTER = "FILTER";

    /**
     * String representing an Order object type.
     */
    public static final String ORDER = "ORDER";

    /**
     * The limit of lines the buffer reader can read forwards when invoking the mark() method.
     */
    private static final int BUFFER_READ_AHEAD_LIMIT = 1;

    /**
     * The char by which a line in the command file should be split when parsing the data.
     */
    private static final String PARSING_DELIMITER = "#";

    /**
     * The default order type to be created if a Type One error occurs when trying to create a different
     * type of filter.
     */
    private static final String DEFAULT_ORDER = "abs";


    /**
     * A string representing that a filter should return all the files that don't pass the filter.
     */
    private static final String NOT_FILTER = "NOT";

    /**
     * A string representing that the files should be returned in reversed order to the order they were given.
     */
    private static final String REVERSE_ORDER = "REVERSE";

    /**
     * A counter that tracks the lines that are read in the command file.
     */
    public static int lineCounter = 0;

    /**
     * A buffered reader that reads the command file.
     */
    private static BufferedReader bufferedReader;

    /**
     * A method that parses the directory in which the files to be sorted are held.
     *
     * @param dirSrc A directory name in the form of a path (either relative or absolute.)     *
     * @return An array list of all the files that were in the source directory.
     * @throws TypeTwoExceptions These are exceptions that catch an error due to problems related to the
     *                           source directory, command file (including bad format of the file) and IO
     *                           problems. TypeTwoExceptions cause the program to stop running.
     */
    static ArrayList<File> parseDirectory(String dirSrc) throws TypeTwoExceptions {
        File files = new File(dirSrc);

        //A list containing all the files and other directories. May also be null.
        File[] allFiles = files.listFiles();

        //ArrayList into which only files (and not directories) are stored.
        ArrayList<File> fileArrayList = new ArrayList<>();

        if (allFiles == null) {
            throw new TypeTwoExceptions(TypeTwoExceptions.NO_SRC_FILE);
        }
        for (File file : allFiles) {
            if (file.isFile()) {
                fileArrayList.add(file);
            }
        }
        return fileArrayList;


    }

    /**
     * A method that parses the data in the command file
     *
     * @param commandFile The file which has the instructions for creating the filters and orders
     * @return An array list of all the sections that were created from the instructions in the command file.
     * @throws IOException       An exception thrown whenever an input or output operation
     *                           is failed or interpreted.
     * @throws TypeTwoExceptions These are exceptions that catch an error due to problems related to the
     *                           source directory, command file (including bad format of the file) and IO
     *                           problems. TypeTwoExceptions cause the program to stop running.
     */
    public static ArrayList<Section> parseCommandFile(File commandFile) throws IOException,
            TypeTwoExceptions {

        //Initializes an ArrayList into which the section will be stored.
        ArrayList<Section> sectionArray = new ArrayList<>();

        //Initialize file reader and buffer
        FileReader inputFile = new FileReader(commandFile);
        bufferedReader = new BufferedReader(inputFile);
        String currLine;

        //Initialize null Order and Filter
        Order order;
        Filter filter;

        while ((currLine = nextLine()) != null) {

            if (currLine.equals(FILTER)) {
                String secondLine = nextLine();
                filter = createFilter(secondLine);

                String thirdLine = nextLine();
                if (!thirdLine.equals(ORDER)) {
                    throw new TypeTwoExceptions(TypeTwoExceptions.BAD_FORMAT_MSG);

                } else { //the line says ORDER
                    bufferedReader.mark(BUFFER_READ_AHEAD_LIMIT);
                    String orderInstructions = nextLine();
                    order = createOrder(orderInstructions);
                }

            } else {
                throw new TypeTwoExceptions(TypeTwoExceptions.BAD_FORMAT_MSG);
            }

            //Creating a new section that includes the order and filter object
            Section section = new Section(filter, order);
            sectionArray.add(section);

        }
        bufferedReader.close();
        return sectionArray;
    }


    /**
     * Reads the next line in the file using the buffered reader, and increases the line count.
     *
     * @return The next line in the file.
     * @throws IOException An exception thrown whenever an input or output operation is failed or interpreted.
     */
    private static String nextLine() throws IOException {
        lineCounter++;
        return bufferedReader.readLine();
    }

    /**
     * A method that creates a filter object according the the instructions in the command file.
     *
     * @param filterInstructions A string containing the instructions for creating the filter.
     * @return A filter object (either the default filter if there was a problem creating the filter, or a
     * specified filter according to user input.)
     * @throws TypeTwoExceptions hese are exceptions that catch an error due to problems related to the
     *                           source directory, command file (including bad format of the file) and IO
     *                           problems. TypeTwoExceptions cause the program to stop running.
     */
    private static Filter createFilter(String filterInstructions) throws TypeTwoExceptions {

        if (filterInstructions == null) {
            throw new TypeTwoExceptions(TypeTwoExceptions.BAD_FORMAT_MSG);
        }

        String[] parsedLine = parseLine(filterInstructions);
        String[] params = parseParams(parsedLine);
        return FilterFactory.getFilter(parsedLine[0], checkIfIsNot(params), params);
    }

    /**
     * A method that creates an Order object according the the instructions in the command file.
     *
     * @param orderInstructions A string containing the instructions for creating the order object.
     * @return An order object (either the default order if there was a problem creating the order, or a
     * specified order according to the user input.)
     * @throws IOException An exception thrown whenever an input or output operation is failed or interpreted.
     */
    private static Order createOrder(String orderInstructions) throws IOException {
        //Initialize Order object
        Order order;

        if ((orderInstructions == null) || (orderInstructions.equals(FILTER))) {
            order = OrderFactory.getOrder(DEFAULT_ORDER, false);
            bufferedReader.reset(); //reset the buffered reader to the marked line.
            lineCounter--; //updates the count since the buffered reader was moved backwards.

        } else {
            String[] parsedLine = parseLine(orderInstructions);
            String[] params = parseParams(parsedLine);
            order = OrderFactory.getOrder(parsedLine[0], checkIfIsReverse(params));
        }
        return order;
    }

    /**
     * A method that parses one line in a file.
     *
     * @param line The line to be parsed.
     * @return A String array that holds each part of the line that was parsed.
     */
    private static String[] parseLine(String line) {
        if (line != null) {
            return line.split(PARSING_DELIMITER);
        }
        return null;

    }

    /**
     * A method that parses the parameters of a filter or order by copying them into a new array, excluding
     * the filter/order name.
     *
     * @param instructions the String array to be parsed.
     * @return A string array containing only the parameters,
     */
    private static String[] parseParams(String[] instructions) {
        String[] params;

        params = new String[instructions.length - 1];
        System.arraycopy(instructions, 1, params, 0, params.length);

        return params;
    }


    /**
     * Checks if the last parameter given in the filter line is the word "NOT".
     *
     * @param params A string array containing the text from the filter instructions.
     * @return True - if the last parameter is the word "NOT", false - otherwise.
     */
    private static boolean checkIfIsNot(String[] params) {
        return params.length != 0 && params[params.length - 1].equals(NOT_FILTER);
    }

    /**
     * Checks if the last parameter given in the order line is the word "REVERSE".
     *
     * @param params A string array containing the text from the order instructions.
     * @return True - if the last parameter is the word "REVERSE", false - otherwise.
     */
    private static boolean checkIfIsReverse(String[] params) {
        return params.length != 0 && params[params.length - 1].equals(REVERSE_ORDER);
    }
}