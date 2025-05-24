import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArrayOfObjects {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarGalleryGUI().createAndShowGUI());
    }
}

class CarGalleryGUI {
    private final Car[] carArray = {
        new Car("Toyota", "Camry", 2020, 25000, false),
        new Car("Honda", "Civic", 2016, 15000, true),
        new Car("BMW", "X5", 2021, 50000, false),
        new Car("Ford", "Fusion", 2019, 20000, false)
    };

    private JTextArea output;
    private JComboBox<String> carSelector;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Car Gallery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 450);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton btnAll = new JButton("Show All Cars");
        JButton btnAvailable = new JButton("Available Cars");
        JButton btnMostExpensive = new JButton("Most Expensive");
        JButton btnAverage = new JButton("Average Price");

        topPanel.add(btnAll);
        topPanel.add(btnAvailable);
        topPanel.add(btnMostExpensive);
        topPanel.add(btnAverage);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel leasePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        carSelector = new JComboBox<>();
        for (int i = 0; i < carArray.length; i++) {
            carSelector.addItem(i + ": " + carArray[i].make + " " + carArray[i].model);
        }
        JButton btnLease = new JButton("Lease Selected Car");
        leasePanel.add(new JLabel("Select a car:"));
        leasePanel.add(carSelector);
        leasePanel.add(btnLease);
        frame.add(leasePanel, BorderLayout.SOUTH);

        output = new JTextArea();
        output.setEditable(false);
        output.setMargin(new Insets(10, 10, 10, 10));
        frame.add(new JScrollPane(output), BorderLayout.CENTER);

        btnAll.addActionListener(e -> displayAll());
        btnAvailable.addActionListener(e -> displayAvailable());
        btnMostExpensive.addActionListener(e -> displayMostExpensive());
        btnAverage.addActionListener(e -> displayAveragePrice());
        btnLease.addActionListener(e -> leaseSelectedCar());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displayAll() {
        output.setText("All Cars:\n");
        for (Car c : carArray) {
            output.append(c.getInfo() + "\n");
        }
    }

    private void displayAvailable() {
        output.setText("Available Cars:\n");
        for (Car c : carArray) {
            if (!c.isLeased) output.append(c.getInfo() + "\n");
        }
    }

    private void displayMostExpensive() {
        Car max = carArray[0];
        for (Car c : carArray) {
            if (c.price > max.price) max = c;
        }
        output.setText("Most Expensive Car:\n" + max.getInfo());
    }

    private void displayAveragePrice() {
        double sum = 0;
        for (Car c : carArray) sum += c.price;
        double avg = sum / carArray.length;
        output.setText("Average Car Price: $" + avg);
    }

    private void leaseSelectedCar() {
        int index = carSelector.getSelectedIndex();
        if (!carArray[index].isLeased) {
            carArray[index].isLeased = true;
            output.setText("Car leased successfully:\n" + carArray[index].getInfo());
        } else {
            output.setText("Car is already leased:\n" + carArray[index].getInfo());
        }
    }
}

class Car {
    String make;
    String model;
    int year;
    int price;
    boolean isLeased;

    public Car(String make, String model, int year, int price, boolean isLeased) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isLeased = isLeased;
    }

    public String getInfo() {
        return make + " " + model + " (" + year + ") - $" + price + (isLeased ? " [LEASED]" : " [AVAILABLE]");
    }
}
