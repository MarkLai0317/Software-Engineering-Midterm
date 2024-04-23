
public class Borrower{
    private String name;
    private int predefined_borrow_book_number;
    private list<int> booksBorrowed = new ArrayList<int>();

    public Borrower(String name, int predefined_borrow_book_number){
        this.name = name;
        this.predefined_borrow_book_number = predefined_borrow_book_number;
    }

    public String getName(){
        return this.name;
    }

    public int getPredefinedBorrowBookNumber(){
        return this.predefined_borrow_book_number;
    }

    public ArrayList<int> getBorrowedBook(){
        return this.booksBorrowed;
    }

    public void addBorrowedBook(int book_id){
        this.booksBorrowed.add(book_id);
    }

    public void removeBorrowedBooks(int book_id){
        this.booksBorrowed.remove(book_id);
    }
}
