import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LibrarySystem {
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
            int numBooks = Integer.parseInt(reader.readLine());
            List<Book> books = new ArrayList<Book>();
            for (int i = 0; i < numBooks; i++) {
                String[] bookDetails = reader.readLine().split(" ");
                String Author = bookDetails[0];
                String Subject = bookDetails[1];
                Book book = new Book(Author, Subject);
                books.add(book);
            }
            Library library = new Library(books);
            int numUsers = Integer.parseInt(reader.readLine());
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
                    System.out.println(line);
                    String[] lineArray = line.split(" ");
                    if (lineArray[1].equals("addBook")) {
                        String[] bookDetails = reader.readLine().split(" ");
                        String Author = bookDetails[0];
                        String Subject = bookDetails[1];
                        Book book = new Book(Author, Subject);
                        library.addBook(lineArray[0], book);

                    } else if (lineArray[1].equals("removeBook")) {
                        library.removeBook(lineArray[0], Integer.parseInt(lineArray[2]));

                    } else if (lineArray[1].equals("checkout")) {
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

                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }

            }

        } catch (IOException e) {
            System.err.println("Error");
            System.exit(1);
        }
    }
}
