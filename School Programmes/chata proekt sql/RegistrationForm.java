package com.loginapp.ui;

import com.loginapp.database.UserDAO;
import com.loginapp.models.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField countryField;
    private JSpinner dateOfBirthSpinner;
    private JSpinner ageSpinner;
    private JLabel profilePictureLabel;
    private JButton uploadPictureButton;
    private JButton registerButton;
    private JButton cancelButton;
    private byte[] profilePictureData;
    private JFrame parentFrame;
    
    public RegistrationForm(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(parentFrame);
        setResizable(false);
        
        initComponents();
        setupListeners();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Username
        addLabeledField(mainPanel, "Username:", usernameField = new JTextField(20));
        
        // Password
        addLabeledField(mainPanel, "Password:", passwordField = new JPasswordField(20));
        
        // First Name
        addLabeledField(mainPanel, "First Name:", firstNameField = new JTextField(20));
        
        // Last Name
        addLabeledField(mainPanel, "Last Name:", lastNameField = new JTextField(20));
        
        // Email
        addLabeledField(mainPanel, "Email:", emailField = new JTextField(20));
        
        // Phone
        addLabeledField(mainPanel, "Phone:", phoneField = new JTextField(20));
        
        // Address
        addLabeledField(mainPanel, "Address:", addressField = new JTextField(20));
        
        // City
        addLabeledField(mainPanel, "City:", cityField = new JTextField(20));
        
        // Country
        addLabeledField(mainPanel, "Country:", countryField = new JTextField(20));
        
        // Date of Birth
        dateOfBirthSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateOfBirthSpinner, "yyyy-MM-dd");
        dateOfBirthSpinner.setEditor(dateEditor);
        addLabeledField(mainPanel, "Date of Birth:", dateOfBirthSpinner);
        
        // Age
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        addLabeledField(mainPanel, "Age:", ageSpinner);
        
        // Profile Picture
        JPanel picturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        picturePanel.setBackground(new Color(240, 240, 240));
        uploadPictureButton = new JButton("Upload Picture");
        uploadPictureButton.setBackground(new Color(70, 130, 180));
        uploadPictureButton.setForeground(Color.WHITE);
        profilePictureLabel = new JLabel("No picture selected");
        picturePanel.add(uploadPictureButton);
        picturePanel.add(profilePictureLabel);
        
        JPanel pictureLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pictureLabelPanel.setBackground(new Color(240, 240, 240));
        pictureLabelPanel.add(new JLabel("Profile Picture:"));
        mainPanel.add(pictureLabelPanel);
        mainPanel.add(picturePanel);
        mainPanel.add(Box.createVerticalStrut(10));
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(100, 30));
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(178, 34, 34));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(100, 30));
        
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        
        mainPanel.add(buttonPanel);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
    }
    
    private void addLabeledField(JPanel panel, String labelText, JComponent field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(new Color(240, 240, 240));
        
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 20));
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        
        fieldPanel.add(label);
        fieldPanel.add(field);
        
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(5));
    }
    
    private void setupListeners() {
        uploadPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadProfilePicture();
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegistration();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void uploadProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                profilePictureData = readFileToByteArray(file);
                profilePictureLabel.setText("File: " + file.getName() + " (" + profilePictureData.length + " bytes)");
                profilePictureLabel.setForeground(new Color(34, 139, 34));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private byte[] readFileToByteArray(File file) throws IOException {
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }
    
    private void handleRegistration() {
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String city = cityField.getText().trim();
        String country = countryField.getText().trim();
        Date dateOfBirth = (Date) dateOfBirthSpinner.getValue();
        int age = (Integer) ageSpinner.getValue();
        
        // Check if username already exists
        if (UserDAO.usernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists!",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create user object
        User newUser = new User(username, password, firstName, lastName, dateOfBirth,
                age, email, phone, address, city, country, profilePictureData);
        
        // Register user
        if (UserDAO.registerUser(newUser)) {
            JOptionPane.showMessageDialog(this, "Registration successful!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validateInputs() {
        if (usernameField.getText().trim().isEmpty() ||
            passwordField.getPassword().length == 0 ||
            firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() ||
            cityField.getText().trim().isEmpty() ||
            countryField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validate email format
        if (!emailField.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address!",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validate password length
        if (passwordField.getPassword().length < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters!",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
}