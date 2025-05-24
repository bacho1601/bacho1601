import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementSystem {
    private final Book[] library = {
        new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
        new Book("1984", "George Orwell", 1949),
        new Book("To Kill a Mockingbird", "Harper Lee", 1960),
        new Book("Moby Dick", "Herman Melville", 1851)
    };

    private JTextArea output;
    private JPanel actionPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        JButton btnDisplay = new JButton("Display All Books");
        JButton btnBorrow = new JButton("Borrow Book");
        JButton btnReturn = new JButton("Return Book");
        JButton btnUpdate = new JButton("Update Book Info");
        Dimension btnSize = new Dimension(150, 35);
        for (JButton b : new JButton[]{btnDisplay, btnBorrow, btnReturn, btnUpdate}) b.setPreferredSize(btnSize);

        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnBorrow);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnUpdate);
        frame.add(buttonPanel, BorderLayout.NORTH);

        output = new JTextArea();
        output.setEditable(false);
        output.setMargin(new Insets(10, 10, 10, 10));
        frame.add(new JScrollPane(output), BorderLayout.CENTER);

        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(actionPanel, BorderLayout.SOUTH);

        btnDisplay.addActionListener(e -> {
            clearActionPanel();
            displayBooks();
        });

        btnBorrow.addActionListener(e -> {
            clearActionPanel();
            borrowBookUI();
        });

        btnReturn.addActionListener(e -> {
            clearActionPanel();
            returnBookUI();
        });

        btnUpdate.addActionListener(e -> {
            clearActionPanel();
            updateBookUI();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void clearActionPanel() {
        actionPanel.removeAll();
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private void displayBooks() {
        output.setText("--- Library Inventory ---\n");
        for (Book b : library) {
            output.append(b.getDetails() + "\n");
        }
    }

    private void borrowBookUI() {
        JComboBox<String> bookSelector = new JComboBox<>();
        for (Book b : library) {
            if (b.getBorrowerName() == null) {
                bookSelector.addItem(b.title);
            }
        }
        if (bookSelector.getItemCount() == 0) {
            output.setText("There are no available books to borrow.");
            return;
        }
        JTextField borrowerField = new JTextField(10);
        JButton confirm = new JButton("Confirm Borrow");

        actionPanel.add(new JLabel("Select Book:"));
        actionPanel.add(bookSelector);
        actionPanel.add(new JLabel("Borrower Name:"));
        actionPanel.add(borrowerField);
        actionPanel.add(confirm);

        confirm.addActionListener(e -> {
            String selectedTitle = (String) bookSelector.getSelectedItem();
            int index = -1;
            for (int i = 0; i < library.length; i++) {
                if (library[i].title.equals(selectedTitle)) {
                    index = i;
                    break;
                }
            }
            // removed duplicate index declaration
            String borrower = borrowerField.getText().trim();
            if (borrower.isEmpty()) {
                output.setText("Please enter a borrower name.");
                return;
            }
            if (library[index].getBorrowerName() == null) {
                library[index].borrowBook(borrower);
                output.setText("Book borrowed successfully!\n" + library[index].getDetails());
            } else {
                output.setText("Book is already borrowed by " + library[index].getBorrowerName());
            }
            clearActionPanel();
        });
    }

    private void returnBookUI() {
        JComboBox<String> bookSelector = new JComboBox<>();
        for (Book b : library) {
            if (b.getBorrowerName() != null) {
                bookSelector.addItem(b.title);
            }
        }
        if (bookSelector.getItemCount() == 0) {
            output.setText("There are no borrowed books to return.");
            return;
        }
        JButton confirm = new JButton("Confirm Return");

        actionPanel.add(new JLabel("Select Book:"));
        actionPanel.add(bookSelector);
        actionPanel.add(confirm);

        confirm.addActionListener(e -> {
            int index = -1;
            String selectedTitle = (String) bookSelector.getSelectedItem();
            for (int i = 0; i < library.length; i++) {
                if (library[i].title.equals(selectedTitle)) {
                    index = i;
                    break;
                }
            }
            if (index != -1 && library[index].getBorrowerName() != null) {
                library[index].returnBook();
                output.setText("Book returned successfully!\n" + library[index].getDetails());
            } else {
                output.setText("Book is not currently borrowed.");
            }
            clearActionPanel();
        });
    }

    private void updateBookUI() {
        JComboBox<String> bookSelector = new JComboBox<>();
        for (Book b : library) bookSelector.addItem(b.title);
        JButton confirm = new JButton("Confirm Update");

        actionPanel.add(new JLabel("Select Book:"));
        actionPanel.add(bookSelector);
        actionPanel.add(confirm);

        confirm.addActionListener(e -> {
            int index = bookSelector.getSelectedIndex();
            String newTitle = JOptionPane.showInputDialog("Enter new title:", library[index].title);
            String newAuthor = JOptionPane.showInputDialog("Enter new author:", library[index].author);
            String yearStr = JOptionPane.showInputDialog("Enter new year:", library[index].yearPublished);
            try {
                int newYear = Integer.parseInt(yearStr);
                library[index].updateBookInfo(newTitle, newAuthor, newYear);
                output.setText("Book updated successfully!\n" + library[index].getDetails());
            } catch (NumberFormatException ex) {
                output.setText("Invalid year entered.");
            }
            clearActionPanel();
        });
    }
}

class Book {
    String title;
    String author;
    int yearPublished;
    String borrowerName;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = null;
    }

    public String getDetails() {
        String status = (borrowerName == null) ? "Available" : "Borrowed by " + borrowerName;
        return title + " by " + author + " (" + yearPublished + ") - " + status;
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished) {
        this.title = newTitle;
        this.author = newAuthor;
        this.yearPublished = newYearPublished;
    }

    public void borrowBook(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void returnBook() {
        this.borrowerName = null;
    }

    public String getBorrowerName() {
        return borrowerName;
    }
}
