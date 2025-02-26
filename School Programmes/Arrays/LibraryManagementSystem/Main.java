import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Book[] books;
        boolean working = true;
        do {
            System.out.println("===== Library Management System =====");
            System.out.println("1. Display library");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Find a book's index by title");
            System.out.println("5. Exit");
            System.out.println();
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

        } while (working);

    }
    //I'm doing this as I want the inputs to be usable with both indeces and
    //titles of the books in one place (i.e., the user can input either the
    //index or the title of the book, in one place)
    int getBookInteger(Book[] books, String searchTitle){
        int i = -1;
        try {
            i = Integer.parseInt(searchTitle);
        } catch (NumberFormatException e) {
            i = findBookByTitle(books, searchTitle);
        }
        return i;
    }

    void displaylibrary(Book[] books){
        for (int i = 0; i < books.length; i++){
            System.out.print(i+1 +". ");
            books[i].getDetails();
        }
    }
    void borrowBook(Book[] books, String searchTitle, Scanner scanner){
        int i = getBookInteger(books, searchTitle);
        System.out.println("Enter the name of the borrower being registered:");
        String newBorrower = scanner.nextLine();
        books[i].borrowBook();
    }
    void returnBook(Book[] books, String searchTitle) {
        int i = getBookInteger(books, searchTitle);
        books[i].returnbook();
    }
    int findBookByTitle(Book[] books, String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.contains(title)) {
                return i+1;
            }
        }
        return 0;
    }
}

class Book {
    String title;
    String author;
    int yearPublished;
    String borrowerName;

    Book(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getDetails(){
        return "Title: " + title +"\nAuthor: " + author
                  + "\nYear Published: " + yearPublished + "\n";
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished) {
        title = newTitle;
        author = newAuthor;
        yearPublished = newYearPublished;
    }

    public void borrowBook(String newBorrowerName){
        borrowerName = newBorrowerName;
    }

    public void returnbook(){
        borrowerName = "";
    }

    public String getBorrowerName(){
        return borrowerName;
    }
}
