package com.turf.dao;

import com.turf.model.User;
import com.turf.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to register a new user
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        // try-with-resources: This automatically closes the connection when done
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Replacing the '?' with actual data from the User object
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());

            // executeUpdate() is used for INSERT, UPDATE, DELETE
            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0; // Returns true if the user was saved

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to check login credentials
    public User loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // executeQuery() is used for SELECT statements
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // If we found a match, create a User object with the data from the DB
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));

                return user; // Success!
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // If no user found or password wrong
    }
}