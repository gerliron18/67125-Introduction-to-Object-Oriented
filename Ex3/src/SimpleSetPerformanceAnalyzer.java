import java.util.LinkedList;
import java.util.*;

public class SimpleSetPerformanceAnalyzer {

    /**
     * Initial class instances.
     */
    private static final String DATA1 = "data1.txt";
    private static final String DATA2 = "data2.txt";

    private static final int MIL_SECOND_FACTOR = 1000000;
    private static final int NEED_TO_BE_WARMED = 50000;

    private static final String HI_CHECK = "hi";
    private static final String SAME_HASH = "-13170890158";
    private static final String NUM_CHECK = "23";


    /**
     * An array of possible sets.
     */
    private static SimpleSet[] analyzeArray;


    /*----=  Instance Methods  =-----*/


    /**
     * Analyze the runtime differences between given data sets.
     *
     * @param args sets we will analyze between them
     */
    public static void main(String[] args) {
        String[] data1 = Ex3Utils.file2array(DATA1);
        String[] data2 = Ex3Utils.file2array(DATA2);

        initiateAnalyser();

        for (SimpleSet setType : analyzeArray) {
            System.out.println("data 1: " + setType);
            System.out.println("insert data 1 : " + InsertionTest(data1, setType));
            System.out.println("hi data1: " + ContainTest(HI_CHECK, setType, true));
            System.out.println("-13170890158 data1: " + ContainTest(SAME_HASH, setType, true));
        }

        initiateAnalyser();

        for (SimpleSet setType : analyzeArray) {
            System.out.println("data 2: " + setType);
            System.out.println("insert data 2: " + InsertionTest(data2, setType));
            System.out.println("hi data 2: " + ContainTest(HI_CHECK, setType, true));
            System.out.println("23 data 2: " + ContainTest(NUM_CHECK, setType, true));
        }
    }


    /**
     * Initiate an array of the possible types of sets:
     * OpenHashSet, ClosedHashSet, TreeSet, LinkedList, HashSet.
     */
    public static void initiateAnalyser() {
        analyzeArray = new SimpleSet[5];
        analyzeArray[0] = new OpenHashSet();
        analyzeArray[1] = new ClosedHashSet();
        analyzeArray[2] = new CollectionFacadeSet(new TreeSet<String>());
        analyzeArray[3] = new CollectionFacadeSet(new LinkedList<String>());
        analyzeArray[4] = new CollectionFacadeSet(new HashSet<String>());
    }


    /**
     * A time tester which valuates the time it takes to add the given strings array to a given set.
     *
     * @param data Array of strings
     * @param set The need to be checked set
     * @return The time it took to add a string value to the set
     */
    public static double InsertionTest(String[] data, SimpleSet set) {
        long timeBefore = System.nanoTime();

        for (String value : data) {
            set.add(value);
        }

        long difference = System.nanoTime() - timeBefore;

        return ((double) difference / MIL_SECOND_FACTOR);
    }


    /**
     * A time tester which valuates the time it takes to check if the given set contains a given string.
     *
     * @param str The string value
     * @param set The need to be checked set
     * @param WarmMeUp A value refers to if the system need to be warmed before calculate
     * @return The time it took to check if the set contains the given string
     */
    public static double ContainTest(String str, SimpleSet set, boolean WarmMeUp) {
        long timeBefore = System.nanoTime();

        int runAttempts;

        runAttempts = NEED_TO_BE_WARMED;

        for (int i = 0; i <= runAttempts; i++) {
            set.contains(str);
        }

        long difference = System.nanoTime() - timeBefore;

        return ((double) difference / runAttempts);
    }
}
