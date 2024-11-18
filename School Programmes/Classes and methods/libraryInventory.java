public class library {
    public static void main(String[] args) {
        Book p1 = new Book("The Glamour and The Glitz", "Steven Pritz", 5);
        Book p2 = new Book("On the Creation of Life and Existence itself", "Karl Jijek", 5);
        // Task 4: Create two or more Book objects with different attributes.
        p1.displayInfo();
        p2.displayInfo();
        // Task 5: Call the displayDetails method for each object.
    }
}

// Define the Book class
class Book {
    String title;
    String author;
    int numberOfPages;

    // Constructor
    public Book(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public void displayInfo(){
        System.out.println("Title: "+ title);
        System.out.println("Author: "+ author);
        System.out.println("Number of pages: "+ numberOfPages);
    }
}
