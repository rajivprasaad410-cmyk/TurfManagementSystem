package com.turf.ui;

import com.turf.dao.UserDAO;
import com.turf.model.User;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passField;
    private JButton registerBtn, backBtn;
    private UserDAO userDAO = new UserDAO();

    public RegisterFrame() {
        setTitle("Turf Management - Sign Up");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel titleLabel = new JLabel("Create New Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Full Name"));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        registerBtn = new JButton("Register Now");
        registerBtn.setBackground(new Color(46, 204, 113));
        registerBtn.setForeground(Color.WHITE);

        backBtn = new JButton("Already have an account? Login");

        add(titleLabel);
        add(nameField);
        add(emailField);
        add(passField);
        add(registerBtn);
        add(backBtn);

        // Register Button Logic
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String pass = new String(passField.getPassword());

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            User newUser = new User(0, name, email, pass, "CUSTOMER");
            if (userDAO.registerUser(newUser)) {
                JOptionPane.showMessageDialog(this, "✅ Registration Successful! Please Login.");
                new LoginFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Registration Failed. Email might be taken.");
            }
        });

        // Back to Login Logic
        backBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        setVisible(true);
    }
}