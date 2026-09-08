package com.turf.ui;

import com.turf.dao.UserDAO;
import com.turf.model.User;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passField;
    private JButton loginBtn, signUpBtn;
    private UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        setTitle("Turf Management - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel title = new JLabel("Turf Booking Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(52, 152, 219));
        loginBtn.setForeground(Color.WHITE);

        signUpBtn = new JButton("Don't have an account? Sign Up");

        add(title);
        add(emailField);
        add(passField);
        add(loginBtn);
        add(signUpBtn);

        // Login Logic
        loginBtn.addActionListener(e -> {
            User user = userDAO.loginUser(emailField.getText(), new String(passField.getPassword()));
            if (user != null) {
                new DashboardFrame(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }
        });

        // Sign Up Logic (Opens the RegisterFrame)
        signUpBtn.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });

        setVisible(true);
    }
}