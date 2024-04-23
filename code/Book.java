public class Book {
    // Private fields
    private int id;
    private String author;
    private String subject;

    // Constructor
    public Book(String author, String subject) {
        this.author = author;
        this.subject = subject;
    }
    //set id
    public void setId(int id){
        this.id = id;
    }


    // Public getter for id
    public int getId() {
        return this.id;
    }

    // Public getter for author
    public String getAuthor() {
        return this.author;
    }

    // Public getter for subject
    public String getSubject() {
        return this.subject;
    }

    // Public method to print book details
    public void printBook() {
        System.out.println("ID: " + id + "Author: " + author + "Subject: " + subject);
    }

}

