import java.util.Scanner;

public class NewMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Initialize the bookstore inventory
        Book[] books = {
            new Book("Book1", 10.99, 5),
            new Book("Book2", 15.99, 3),
            new Book("Book3", 8.99, 10)
        };

        boolean working = true;
        do {
            System.out.println("===== Online Bookstore =====");
            System.out.println("1. Display available books");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Purchase a book");
            System.out.println("4. Exit");
            System.out.println();
            int choice = scan.nextInt();
            scan.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayBooks(books);
                    break;
                case 2:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scan.nextLine();
                    searchBook(books, searchTitle);
                    break;
                case 3:
                    System.out.print("Enter book code to purchase (index): ");
                    int bookIndex = scan.nextInt();
                    System.out.print("Enter payment amount: ");
                    double payment = scan.nextDouble();
                    purchaseBook(books, bookIndex, payment);
                    break;
                case 4:
                    working = false;
                    break;
                default:
                    System.out.println("Unexpected value: " + choice);
            }

        } while (working);

        scan.close();
    }

    static void displayBooks(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(books[i].getDetails());
        }
    }

    static void searchBook(Book[] books, String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println(book.getDetails());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with title: " + title);
        }
    }

    static void purchaseBook(Book[] books, int index, double payment) {
        if (index < 1 || index > books.length) {
            System.out.println("Invalid book code.");
            return;
        }

        Book book = books[index - 1];
        if (payment >= book.price) {
            if (book.quantity > 0) {
                book.updateQuantity(book.quantity - 1);
                System.out.println("Purchase successful! Remaining balance: $" + (payment - book.price));
            } else {
                System.out.println("Book out of stock.");
            }
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

class Book {
    String title;
    double price;
    int quantity;

    Book(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetails() {
        return "Title: " + title + "\nPrice: $" + price + "\nQuantity: " + quantity + "\n";
    }

    public void updateQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}
