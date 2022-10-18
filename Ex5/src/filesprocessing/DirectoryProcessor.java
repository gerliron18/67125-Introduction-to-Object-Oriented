package filesprocessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that manages and runs the whole program - from getting the args to returning a list of the files
 * according to the instructions in the command file.
 *
 * @author Liron Gershony and Chana Goldstein
 */

public class DirectoryProcessor {

    /**
     * The array list in which the files from the file directory are stored.
     */
    private ArrayList<File> fileArrayList;

    /**
     * The file that contains the filter and order instructions
     */
    private File commandFile;

    /**
     * A constructor for the directory processor
     *
     * @param args The arguments the program is called with from the command line (a path to the directory
     *             of files to sort, and a command file)
     * @throws TypeTwoExceptions These are exceptions that catch an error due to problems related to the
     *                           source directory, command file (including bad format of the file)
     *                           and IO problems. TypeTwoExceptions cause the program to stop running.
     */
    private DirectoryProcessor(String[] args) throws TypeTwoExceptions {
        this.fileArrayList = ParseData.parseDirectory(args[0]);
        this.commandFile = new File(args[1]);
    }

    /**
     * The main function that runs the program.
     *
     * @param args the Strings that were entered into the command line when the program was called.
     */
    public static void main(String[] args) throws TypeTwoExceptions{
        try {
            if (args.length != 2) {
                throw new TypeTwoExceptions(TypeTwoExceptions.ILLEGAL_PARAM_NUM_MSG);
            }
            DirectoryProcessor directoryProcessor = new DirectoryProcessor(args);
            directoryProcessor.doCommand();

        } catch (IOException e) {
            TypeTwoExceptions.printIOProblem();
        } catch (TypeTwoExceptions ignored) {
        }

    }

    /**
     * The method that calls the parsing of the files. Once it gets an array list of sections, it iterates
     * over the sections and prints the file names.
     *
     * @throws TypeTwoExceptions These are exception that catch an error due to problems related to the
     *                           source directory, command file (including bad format of the file)
     *                           and IO problems. TypeTwoExceptions cause the program to stop running.
     * @throws IOException       An exception thrown whenever an input or output operation
     *                           is failed or interpreted.
     */
    private void doCommand() throws TypeTwoExceptions, IOException {
        ArrayList<Section> sectionArrayList = ParseData.parseCommandFile(this.commandFile);
        for (Section section : sectionArrayList) {
            ArrayList<File> fileToPrint = section.runSection(this.fileArrayList);
            for (File file : fileToPrint) {
                System.out.println(file.getName());
            }
        }
    }
}
