/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library
 * to be able to check out books, if a copy of the requested book is available.
 */
class Library {

    /**
     * The maximal number of books this library can hold.
     */
    int maxBookQuantity;

    /**
     * The maximal number of books this library allows a single patron to borrow at the same time.
     */
    int maxBorrowing;

    /**
     * The maximal number of registered patrons this library can handle.
     */
    int maxClients;

    /**
     * The book list of the library.
     */
    Book[] BookArray;

    /**
     * The Patron list registered at the library.
     */
    Patron[] PatronArray;

    /**
     * The list who manage every Patron books borrowing.
     */
    int[] OneCanBorrowArray;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows
     *                          a single patron to borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        maxBookQuantity = maxBookCapacity;
        maxBorrowing = maxBorrowedBooks;
        maxClients = maxPatronCapacity;
        BookArray = new Book[maxBookQuantity];
        PatronArray = new Patron[maxClients];
        OneCanBorrowArray = new int[maxClients];
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        for (int i = 0; i < BookArray.length; i++) {
            if (BookArray[i] == book) {
                return i;
            } else if (BookArray[i] == null) {
                BookArray[i] = book;
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        if ((bookId <= BookArray.length) && (bookId > -1)) {
            if (BookArray[bookId] == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     *
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i < BookArray.length; i++) {
            if (BookArray[i] == book) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if ((isBookIdValid(bookId)) && (BookArray[bookId].currentBorrowerId == -1)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron
     * was successfully registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        for (int i = 0; i < PatronArray.length; i++) {
            if (PatronArray[i] == patron) {
                return i;
            } else if (PatronArray[i] == null) {
                PatronArray[i] = patron;
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        if ((patronId <= PatronArray.length) && (patronId > -1)) {
            if (PatronArray[patronId] == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * Returns the non-negative id number of the given patron
     * if he is registered to this library, -1 otherwise.
     *
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is
     * registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i < PatronArray.length; i++) {
            if (PatronArray[i] == patron) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing
     * the maximal number of books allowed, and if the patron will enjoy this book.
     *
     * @param bookId   - The id number of the book to borrow.
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if ((isBookAvailable(bookId)) && (isPatronIdValid(patronId)) && (patronOverdo(patronId)) &&
                (PatronArray[patronId].willEnjoyBook(BookArray[bookId]))) {
            BookArray[bookId].setBorrowerId(patronId);
            OneCanBorrowArray[patronId]++;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Check if patron who want to borrow book is out of the library limit of borrowing.
     *
     * @param patronId The id number of the patron that will borrow the book.
     * @return false if the the patron can't borrow one more book, true otherwise.
     */
    boolean patronOverdo(int patronId) {
        if (OneCanBorrowArray[patronId] < maxBorrowing) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the given book.
     *
     * @param bookId - The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (bookId > -1) {
            int patronId = BookArray[bookId].getCurrentBorrowerId();
            if ((isBookIdValid(bookId)) && (isPatronIdValid(patronId))) {
                BookArray[bookId].returnBook();
                OneCanBorrowArray[patronId]--;
            }
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     *
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most.
     * Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        Book bestBook = null;
        int maxScore = 0;
        if (isPatronIdValid(patronId)) {
            for (int i = 0; i < BookArray.length; i++) {
                if (PatronArray[patronId].willEnjoyBook(BookArray[i]) &&
                        (PatronArray[patronId].getBookScore(BookArray[i]) > maxScore)
                        && (isBookAvailable(i))) {
                    bestBook = BookArray[i];
                    maxScore = PatronArray[patronId].getBookScore(BookArray[i]);
                }
            }
        }
        return bestBook;
    }

}
