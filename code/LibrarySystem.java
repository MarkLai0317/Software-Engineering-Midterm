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
            ArrayList<Book> books = new ArrayList<Book>();
            for(int i=0; i<numBooks; i++){
                String[] bookDetails = reader.readLine();
                String Author = bookDetails[0];
                String Subject = bookDetails[1];
                Book book = new Book(i, Author, Subject);
                books.add(book);
            }
            Library library = new Library(books);
            int numUsers = Integer.parseInt(reader.readLine());
            for(int i=0; i<numUsers; i++){
                String[] userDetails = reader.readLine();
                if (userDetails[0].equals("Staff")){
                    String name = userDetails[1];
                    Staff staff = new Staff(name);
                    library.addStaff(staff);
                }
                else if (userDetails[0].equals("Borrower")){
                    String name = userDetails[1];
                    int predefined_borrow_book_number = Integer.parseInt(userDetails[2]);
                    Borrower borrower = new Borrower(name, predefined_borrow_book_number);
                    library.addBorrower(borrower);
                }
            }
            while ((line = reader.readLine()) != null) {
                try {
                    if(line[1].equals("addBook")){
                        
                    }else if(line[1].equals("removeBook")){
                    }else if(line[1].equals("checkout")){
                    }else if(line[1].equals("return"){
                    }else if(line[1].equals("listAuthor")){
                    }else if(line[1].equals("listSubject")){
                    }else if(line[1].equals("findChecked")){
                    }else if(line[1].equals("Borrower")){
                }

            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            System.exit(1);
        }
    }
}
