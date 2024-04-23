import java.io.*;
import java.util.*;

class Library{
    private Map<Integer, String> lastCheckedOutBy;
    private Map<Integer, String> isCheckedOutBy;
    private Map<String, Staff> staffs;
    private Map<String, Borrower> borrowers;
    private Map<Integer, Book> books;
    private int idCounter=0;

    public Library(List<Book> init_books){
        this.lastCheckedOutBy = new LinkedHashMap<>();
        this.isCheckedOutBy = new LinkedHashMap<>();
        this.staffs = new LinkedHashMap<>();
        this.borrowers = new LinkedHashMap<>();
        this.books = new LinkedHashMap<>();
        for(Book book: init_books){
            this.books.put(this.idCounter++, book);
        }
    }

    private void raiseError(){
        throw new Exception("Error");
    }

    private boolean checkStaff(String staffName){
        return this.staffs.containsKey(staffName);
    }

    private boolean checkBorrower(String borrowerName){
        return this.borrowers.containsKey(borrowerName);
    }

    private boolean checkBook(int bookId){
        return this.books.containsKey(bookId);
    }

    private boolean checkBookCheckOut(int bookId){
        return this.isCheckedOutBy.containsKey(bookId);
    }

    private Book getBook(int bookId){
        return this.books.get(bookId);
    }

    public void addStaff(Staff staff){
        this.staffs.put(staff.name, staff);
    }

    public void addBorrower(Borrower borrower){
        this.borrowers.put(borrower.name, borrower);
    }

    public void addBook(String staffName, Book book){
        if(checkStaff(staffName)){
            this.books.put(this.idCounter++, book);
        }
        else if(checkBorrower(staffName)){
            throw new Exception("Borrower can not add book");
        }
        else{
            raiseError();
        }
    }

    public void removeBook(String staffName, int bookId){
        if(!checkBook(bookId)){
            raiseError();
        }
        if(checkStaff(staffName)){
            this.books.remove(bookId);
        }
        else if(checkBorrower(staffName)){
            throw new Exception("Borrower can not remove book");
        }
        else{
            raiseError();
        }
    }

    public List<Book> getBooksByAuthor(String author){
        List<Book> booksByAuthor = new ArrayList<>();
        for(Book book: this.books){
            if(book.author.equals(author)){
                booksByAuthor.add(book);
            }
        }
    
        return booksByAuthor;
      }
    
      public List<Book> getBooksBySubject(String subject){
        List<Book> booksBySubject = new ArrayList<>();
        for(Book book: this.books){
            if(book.subject.equals(subject)){
                booksBySubject.add(book);
            }
        }
    
        return booksBySubject;
      }
    
      public List<Book> findBooksCheckedOutBy(String user, String borrower){
    
        if(checkStaff(user) && checkBorrower(borrower)){
            List<Book> booksCheck = new ArrayList<>();
            for(int bookId: borrower.getBorrowedBooks()){
                booksCheck.add(this.books.get(bookId));
            }
            return booksCheck;
        }
        else if(checkBorrower(user) && !user.equals(borrower)){
            throw new Exception("Borrower can not find books checked out by other users");
        }
        else{
            raiseError();
        }
    
      }

      public Borrower getLastBorrower(String staff, int id){
        
        if(checkStaff(staff)){
            return lastCheckedOutBy.get(id);
        }
        else if(checkBorrower(staff)){
            throw new Exception("Borrower can not find borrower");
        }
        else{
            raiseError();
        }

    }
}
