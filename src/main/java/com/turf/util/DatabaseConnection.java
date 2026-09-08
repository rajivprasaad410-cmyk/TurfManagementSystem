package com.turf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // 1. The Address of your database (The URL)
    private static final String URL = "jdbc:mysql://localhost:3306/turf_management";

    // 2. Your MySQL username (Usually "root")
    private static final String USER = "root";

    // 3. Reads DB_PASSWORD from environment, falls back to placeholder for GitHub safety
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : "YOUR_MYSQL_PASSWORD";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // This line tells Java: "Use the MySQL driver to open a connection"
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to the Turf Database successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace(); // This prints the error details so we can debug
        }
        return connection;
    }
}