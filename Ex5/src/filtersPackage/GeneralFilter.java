package filtersPackage;

import filesprocessing.ParseData;
import filesprocessing.TypeOneExceptions;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that represents a general filter object. The general filter does not filter the files in any
 * way, but just returns all the files in the directory.
 */
public class GeneralFilter implements Filter {

    /**
     * The line number that is being read in the command file.
     */
    public String lineCounter = Integer.toString(ParseData.lineCounter);

    /**
     * A Type One Exception to be thrown in there is a problem generating the filter.
     */
    private TypeOneExceptions typeOneException = null;


    @Override
    public ArrayList<File> doFilter(ArrayList<File> fileArrayList) {
        ArrayList<File> filteredFiles = new ArrayList<>();

        for (File file : fileArrayList) {
            if (isPass(file)) {
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    @Override
    public int getNumOfParams() {
        return 0;
    }

    @Override
    public boolean isPass(File file) {
        return true;
    }

    /**
     * Adds a new Type One Exception to a filter object.
     *
     * @param typeOneException The Type One Exception
     */
    public void addError(TypeOneExceptions typeOneException) {
        this.typeOneException = typeOneException;

    }

    /**
     * Prints the Type One error message.
     */
    public void printError() {
        if (this.typeOneException != null) {
            this.typeOneException.printErrorMSG();
        }
    }
}
