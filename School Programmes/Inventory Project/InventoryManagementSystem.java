import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//product class for inventory items
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return "Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}

//inventory class for Array container of Products
class Inventory {
    private Product[] products = new Product[3];
    private int productCount = 0;

    public String addProduct(Product p) {
        if (productCount < 3) {
            products[productCount++] = p;
            return "Product added successfully.";
        } else {
            return "Inventory full. Cannot add more products.";
        }
    }

    public String updateProduct(String name, double price, int quantity) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                products[i].setPrice(price);
                products[i].setQuantity(quantity);
                return "Product updated successfully.";
            }
        }
        return "Product not found.";
    }

    public String displayAllProducts() {
        if (productCount == 0) {return "Inventory is empty."};
        String displayed  = "";
        for (int i = 0; i < productCount; i++) {
            displayed.concat(products[i].getDetails());
            displayed.concat("\n");
        }
        return displayed.toString();
    }

    public int getProductCount() {
        return productCount;
    }
}

// GUI Class
public class InventoryManagementSystemGUI extends JFrame implements ActionListener {
    private JTextField nameField, priceField, quantityField;
    private JTextArea outputArea;
    private JButton addButton, updateButton, displayButton;
    private JLabel productCountLabel;
    private Inventory inventory;

    public InventoryManagementSystemGUI() {
        inventory = new Inventory();

        setTitle("Inventory Management System");
        setSize(450, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        productCountLabel = new JLabel("Products Entered: 0/3");
        inputPanel.add(productCountLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add Product");
        updateButton = new JButton("Update Product");
        displayButton = new JButton("Display Products");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        displayButton.addActionListener(this);

        outputArea = new JTextArea(10, 35);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateProductCountDisplay() {
        int count = inventory.getProductCount();
        productCountLabel.setText("Products Entered: " + count + "/3");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText().trim();
            String priceStr = priceField.getText().trim();
            String quantityStr = quantityField.getText().trim();

            if (name.isEmpty()) {
                outputArea.setText("Product name cannot be empty.");
                return;
            }
            if (priceStr.isEmpty()) {
                outputArea.setText("Price cannot be empty.");
                return;
            }
            if (quantityStr.isEmpty()) {
                outputArea.setText("Quantity cannot be empty.");
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                int quantity = Integer.parseInt(quantityStr);

                if (price < 0 || quantity < 0) {
                    outputArea.setText("Price and quantity must be non-negative.");
                    return;
                }

                Product p = new Product(name, price, quantity);
                String result = inventory.addProduct(p);
                outputArea.setText(result);
                if (result.equals("Product added successfully.")) {
                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                }
                updateProductCountDisplay();
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter valid numeric values for price and quantity.");
            }

        } else if (e.getSource() == updateButton) {
            String name = nameField.getText().trim();
            String priceStr = priceField.getText().trim();
            String quantityStr = quantityField.getText().trim();

            if (name.isEmpty()) {
                outputArea.setText("Product name cannot be empty.");
                return;
            }
            if (priceStr.isEmpty()) {
                outputArea.setText("Price cannot be empty.");
                return;
            }
            if (quantityStr.isEmpty()) {
                outputArea.setText("Quantity cannot be empty.");
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                int quantity = Integer.parseInt(quantityStr);

                if (price < 0 || quantity < 0) {
                    outputArea.setText("Price and quantity must be non-negative.");
                    return;
                }

                String updateMsg = inventory.updateProduct(name, price, quantity);
                outputArea.setText(updateMsg);
                updateProductCountDisplay();
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter valid numeric values for price and quantity.");
            }

        } else if (e.getSource() == displayButton) {
            String display = inventory.displayAllProducts();
            if (display.equals("Inventory is empty.")) {
                outputArea.setText("No products added yet.");
            } else {
                outputArea.setText(display);
            }
        }
    }

    public static void main(String[] args) {
        new InventoryManagementSystem();
    }
}


class InventoryManagementSystem extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextArea outputArea;
    private JButton addButton;
    private JButton updateButton;
    private JButton displayButton;
    private JLabel productCountLabel;
    private Inventory inventory = new Inventory();

    public InventoryManagementSystem() {
        this.setTitle("Inventory Management System");
        this.setSize(450, 520);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout(10, 10));
        JPanel var1 = new JPanel();
        var1.setLayout(new GridLayout(7, 1, 5, 5));
        var1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        var1.add(new JLabel("Product Name:"));
        this.nameField = new JTextField();
        var1.add(this.nameField);
        var1.add(new JLabel("Price:"));
        this.priceField = new JTextField();
        var1.add(this.priceField);
        var1.add(new JLabel("Quantity:"));
        this.quantityField = new JTextField();
        var1.add(this.quantityField);
        this.productCountLabel = new JLabel("Products Entered: 0/3");
        var1.add(this.productCountLabel);
        JPanel var2 = new JPanel(new FlowLayout(1, 10, 10));
        this.addButton = new JButton("Add Product");
        this.updateButton = new JButton("Update Product");
        this.displayButton = new JButton("Display Products");
        var2.add(this.addButton);
        var2.add(this.updateButton);
        var2.add(this.displayButton);
        this.addButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.displayButton.addActionListener(this);
        this.outputArea = new JTextArea(10, 35);
        this.outputArea.setEditable(false);
        this.outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel var3 = new JPanel();
        var3.setLayout(new BorderLayout());
        var3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        var3.add(new JScrollPane(this.outputArea), "Center");
        this.add(var1, "North");
        this.add(var2, "Center");
        this.add(var3, "South");
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }

    private void updateProductCountDisplay() {
        int var1 = this.inventory.getProductCount();
        this.productCountLabel.setText("Products Entered: " + var1 + "/3");
    }

    public void actionPerformed(ActionEvent var1) {
        if (var1.getSource() == this.addButton) {
            String var13 = this.nameField.getText().trim();
            String var14 = this.priceField.getText().trim();
            String var15 = this.quantityField.getText().trim();
            if (var13.isEmpty()) {
                this.outputArea.setText("Product name cannot be empty.");
                return;
            }

            if (var14.isEmpty()) {
                this.outputArea.setText("Price cannot be empty.");
                return;
            }

            if (var15.isEmpty()) {
                this.outputArea.setText("Quantity cannot be empty.");
                return;
            }

            try {
                double var16 = Double.parseDouble(var14);
                int var17 = Integer.parseInt(var15);
                if (var16 < (double)0.0F || var17 < 0) {
                    this.outputArea.setText("Price and quantity must be non-negative.");
                    return;
                }

                Product var18 = new Product(var13, var16, var17);
                String var9 = this.inventory.addProduct(var18);
                this.outputArea.setText(var9);
                if (var9.equals("Product added successfully.")) {
                    this.nameField.setText("");
                    this.priceField.setText("");
                    this.quantityField.setText("");
                }

                this.updateProductCountDisplay();
            } catch (NumberFormatException var10) {
                this.outputArea.setText("Please enter valid numeric values for price and quantity.");
            }
        } else if (var1.getSource() == this.updateButton) {
            String var12 = this.nameField.getText().trim();
            String var3 = this.priceField.getText().trim();
            String var4 = this.quantityField.getText().trim();
            if (var12.isEmpty()) {
                this.outputArea.setText("Product name cannot be empty.");
                return;
            }

            if (var3.isEmpty()) {
                this.outputArea.setText("Price cannot be empty.");
                return;
            }

            if (var4.isEmpty()) {
                this.outputArea.setText("Quantity cannot be empty.");
                return;
            }

            try {
                double var5 = Double.parseDouble(var3);
                int var7 = Integer.parseInt(var4);
                if (var5 < (double)0.0F || var7 < 0) {
                    this.outputArea.setText("Price and quantity must be non-negative.");
                    return;
                }

                String var8 = this.inventory.updateProduct(var12, var5, var7);
                this.outputArea.setText(var8);
                this.updateProductCountDisplay();
            } catch (NumberFormatException var11) {
                this.outputArea.setText("Please enter valid numeric values for price and quantity.");
            }
        } else if (var1.getSource() == this.displayButton) {
            String var2 = this.inventory.displayAllProducts();
            if (var2.equals("Inventory is empty.")) {
                this.outputArea.setText("No products added yet.");
            } else {
                this.outputArea.setText(var2);
            }
        }

    }

    public static void main(String[] args) {
        new InventoryManagementSystem();
    }
}
