package com.loginapp.ui;

import com.loginapp.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MainForm extends JFrame {
    private User loggedInUser;
    private JLabel welcomeLabel;
    private JLabel userInfoLabel;
    
    public MainForm(User user) {
        this.loggedInUser = user;
        setTitle("Main Application - " + user.getUsername());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(660, 80));
        
        welcomeLabel = new JLabel("Welcome, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        
        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setBorder(BorderFactory.createTitledBorder("User Information"));
        
        userInfoLabel = new JLabel();
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        updateUserInfo();
        
        JScrollPane scrollPane = new JScrollPane(userInfoLabel);
        scrollPane.setBackground(new Color(240, 240, 240));
        contentPanel.add(scrollPane);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setBackground(new Color(70, 130, 180));
        editProfileButton.setForeground(Color.WHITE);
        editProfileButton.setPreferredSize(new Dimension(120, 35));
        editProfileButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Edit Profile functionality to be implemented", "Info", JOptionPane.INFORMATION_MESSAGE));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(178, 34, 34));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(120, 35));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
        
        buttonPanel.add(editProfileButton);
        buttonPanel.add(logoutButton);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void updateUserInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String info = "<html>" +
                "<b>Username:</b> " + loggedInUser.getUsername() + "<br>" +
                "<b>Name:</b> " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "<br>" +
                "<b>Email:</b> " + loggedInUser.getEmail() + "<br>" +
                "<b>Phone:</b> " + loggedInUser.getPhone() + "<br>" +
                "<b>Date of Birth:</b> " + dateFormat.format(loggedInUser.getDateOfBirth()) + "<br>" +
                "<b>Age:</b> " + loggedInUser.getAge() + "<br>" +
                "<b>Address:</b> " + loggedInUser.getAddress() + "<br>" +
                "<b>City:</b> " + loggedInUser.getCity() + "<br>" +
                "<b>Country:</b> " + loggedInUser.getCountry() + "<br>" +
                "</html>";
        userInfoLabel.setText(info);
    }
    
    private void handleLogout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_OPTION) {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            this.dispose();
        }
    }
}