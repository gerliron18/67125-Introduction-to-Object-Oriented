class tester_updated3 {
    public static void main(String[] args) {

        // set up
        Book book1 = new Book("book1", "author1", 2001,
                2, 3, 1);
        Book book2 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book3 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book4 = new Book("book4", "author4", 2004,
                1, 1, 1);

        Patron patron1 = new Patron("patron1", "last1", 3,
                5, 1, 40);
        Patron patron2 = new Patron("patron2", "last2", 2,
                1, 1, 0);
        Patron patron3 = new Patron("patron3", "last3", 2,
                1, 1, 0);

        Library lib = new Library(3, 2, 2);


        // test book
        System.out.println("test Book methods");
        System.out.println();


        System.out.println(book1.stringRepresentation().equals("[book1,author1,2001,6]"));


        System.out.println(book1.getLiteraryValue() == 2 + 3 + 1);

        System.out.println(book1.getCurrentBorrowerId() == -1);

        book1.setBorrowerId(5);
        System.out.println(book1.getCurrentBorrowerId() == 5);
        book1.returnBook();


        // test patron
        System.out.println();

        System.out.println("test Patron methods");
        System.out.println();

        System.out.println(patron1.stringRepresentation().equals("patron1 last1"));

        System.out.println(patron1.getBookScore(book1) == 3 * 2 + 5 * 3 + 1 * 1);

        System.out.println(!patron1.willEnjoyBook(book1));
        System.out.println(patron1.willEnjoyBook(book2));


        // test library
        System.out.println();

        System.out.println("test Library methods");
        System.out.println();

        System.out.println("1: " + (!lib.isBookIdValid(1)));
        System.out.println("1.1: " + (!lib.isBookIdValid(88)));
        System.out.println("1.2: " + (!lib.isBookAvailable(1)));
        System.out.println("2: " +(lib.addBookToLibrary(book1) == 0));
        System.out.println("3: " + lib.isBookIdValid(0));


        System.out.println("3: " + (lib.addBookToLibrary(book2) == 1));
        System.out.println("4: " + (lib.isBookIdValid(1)));
        System.out.println("4.1: " + (lib.addBookToLibrary(book2) == 1)); // add the same book, should
        // return the index of original and shouldn't save another copy.
        System.out.println("5: " + (lib.addBookToLibrary(book3) == 2)); // book with same arguments
        System.out.println("6: " + (lib.addBookToLibrary(book4) == -1));
        System.out.println("4.1: " + (lib.addBookToLibrary(book2) == 1)); // add the same book when there
        // is no room.

        System.out.println("7: " +(!lib.isPatronIdValid(1)));
        System.out.println("7.1: " +(!lib.isPatronIdValid(8)));

        System.out.println("8:" + (lib.registerPatronToLibrary(patron1) == 0));
        System.out.println("8.1:" + (lib.registerPatronToLibrary(patron1) == 0));
        System.out.println("9: " + (lib.isPatronIdValid(0)));

        System.out.println("10: " + (!lib.isPatronIdValid(1)));
        System.out.println("11: " + (lib.registerPatronToLibrary(patron2) == 1));
        System.out.println("12: " + (lib.isPatronIdValid(1)));

        System.out.println("13: " + (lib.registerPatronToLibrary(patron3) == -1));
        System.out.println("14: " + (lib.registerPatronToLibrary(patron1) == 0));


        System.out.println("15: " + (lib.getPatronId(patron2) == 1));

        // tests for 'borrowBook'
        System.out.println("16: " + (lib.isBookAvailable(0)));
        System.out.println("17: " + (!lib.borrowBook(0, 0))); // will not enjoy
        System.out.println("18: " + (lib.borrowBook(0, 1)));
        System.out.println("19: " + (!lib.isBookAvailable(0)));
        System.out.println("20: " + (!lib.borrowBook(7, 1))); // book is id not valid
        System.out.println("21: " + (!lib.borrowBook(1, 9))); // patron id is not valid
        System.out.println("22: " + (lib.borrowBook(1, 1)));
        System.out.println("23: " + (!lib.borrowBook(2, 1))); // too many books for one patron
        System.out.println("24: " + (!lib.borrowBook(1, 0))); // the book is taken
        lib.returnBook(0);
        System.out.println("25: " + (lib.borrowBook(2, 1)));
        System.out.println("26: " + (book1.getCurrentBorrowerId() == -1));


        System.out.println("27: " + (lib.getBookId(lib.suggestBookToPatron(1)) == 0));
        System.out.print("28: " + (lib.suggestBookToPatron(0))); // null because book2 and book3 are
        // taken and he will not enjoy book1
        System.out.println(" <- null is good");
        lib.returnBook(1);


    }
}
