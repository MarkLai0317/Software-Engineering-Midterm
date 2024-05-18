import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;




public class LibrarySystem {

    public static boolean isinteger(String s) {
        try {
            Integer.parseInt(s);
            return true; // String can be parsed as an integer
        } catch (NumberFormatException e) {
            return false; // String cannot be parsed as an integer
        }
    }



    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <inputFile>");
            System.exit(1);
        }

        String inputFileName = args[0];
        // Application app = new Application();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            // Read the first line to get the number of books
            String[] firstLine = reader.readLine().split(" ");
            while (firstLine.length != 1 || !isinteger(firstLine[0])) {
                System.out.println("Error");
                firstLine = reader.readLine().split(" ");
            }
            

            int numBooks = Integer.parseInt(firstLine[0]);
            //int numBooks = Integer.parseInt(reader.readLine());
            List<Book> books = new ArrayList<Book>();
            for (int i = 0; i < numBooks; i++) {
                String[] bookDetails = reader.readLine().split(" ");
                if (bookDetails.length != 2) {
                    System.out.println("Error");
                    continue;
                }
                String Author = bookDetails[0];
                String Subject = bookDetails[1];
                Book book = new Book(Author, Subject);
                books.add(book);
            }
            Library library = new Library(books);
            
            String[] userNum = reader.readLine().split(" ");
            while (userNum.length != 1 || !isinteger(userNum[0])) {
                System.out.println("Error");
                userNum = reader.readLine().split(" ");
            }
            int numUsers = Integer.parseInt(userNum[0]);
            for (int i = 0; i < numUsers; i++) {
                String[] userDetails = reader.readLine().split(" ");
                if (userDetails[0].equals("Staff")) {
                    String name = userDetails[1];
                    Staff staff = new Staff(name);
                    library.addStaff(staff);
                } else if (userDetails[0].equals("Borrower")) {
                    String name = userDetails[1];
                    int predefined_borrow_book_number = Integer.parseInt(userDetails[2]);
                    Borrower borrower = new Borrower(name, predefined_borrow_book_number);
                    library.addBorrower(borrower);
                }
            }
            while ((line = reader.readLine()) != null) {
                try {
                    // System.out.println(line);
                    if (line.equals("")) {
                        continue;
                    }
                    String[] lineArray = line.split(" ");
                    if(lineArray.length < 2){
                        System.out.println("Error");
                        continue;
                    }
                    if (lineArray[1].equals("addBook")) {
                        if (!library.checkStaff(lineArray[0]) && !library.checkBorrower(lineArray[0])) {
                          System.out.println("Error");
                          continue;
                        }
                        String l = reader.readLine();
                        if (l == null) {
                            System.out.println("Error");
                            continue;
                        }
                        String[] bookDetails = l.split(" ");
                        
                        if (bookDetails.length != 2) {
                            System.out.println("Error");
                            continue;
                        }


                        String Author = bookDetails[0];
                        String Subject = bookDetails[1];
                        Book book = new Book(Author, Subject);
                        library.addBook(lineArray[0], book);

                    } else if (lineArray[1].equals("removeBook")) {
                        library.removeBook(lineArray[0], Integer.parseInt(lineArray[2]));

                    } else if (lineArray[1].equals("checkout")) {
                        if (!library.checkStaff(lineArray[0]) && !library.checkBorrower(lineArray[0])) {
                            System.out.println("Error");
                            continue;
                        }
                        if (!library.checkStaff(lineArray[2]) && !library.checkBorrower(lineArray[2])) {
                            System.out.println("Error");
                            continue;
                        }
                        String[] bookIds = reader.readLine().split(" ");
                        for (String bookId : bookIds) {
                            library.checkoutBook(lineArray[0], lineArray[2], Integer.parseInt(bookId));
                        }

                    } else if (lineArray[1].equals("return")) {
                        library.returnBook(lineArray[0], Integer.parseInt(lineArray[2]));

                    } else if (lineArray[1].equals("listAuthor")) {
                        List<Book> booksByAuthor = library.getBooksByAuthor(lineArray[0], lineArray[2]);
                        for (Book book : booksByAuthor) {
                            book.printBook();
                        }

                    } else if (lineArray[1].equals("listSubject")) {
                        List<Book> booksBySubject = library.getBooksBySubject(lineArray[0], lineArray[2]);
                        for (Book book : booksBySubject) {
                            book.printBook();
                        }

                    } else if (lineArray[1].equals("findChecked")) {
                        List<Book> checkedBooks = library.findBooksCheckedOutBy(lineArray[0], lineArray[2]);
                        for (Book book : checkedBooks) {
                            book.printBook();
                        }

                    } else if (lineArray[1].equals("Borrower")) {
                        Borrower borrower = library.getLastBorrower(lineArray[0], Integer.parseInt(lineArray[2]));
                        System.out.println("User: " + borrower.getName());
                    }
                    else {
                    System.out.println("Error");
                    }

                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println("Error");
            System.exit(1);
        }
    }

    private static boolean isInteger(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInteger'");
    }
}
