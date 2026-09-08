package com.turf.dao;

import com.turf.model.Payment;
import com.turf.util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // 1. SAVE A NEW PAYMENT (Used during booking)
    public boolean processPayment(Payment payment) {
        String query = "INSERT INTO payments (booking_id, amount, payment_status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, payment.getBookingId());
            pstmt.setBigDecimal(2, payment.getAmount());
            pstmt.setString(3, payment.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. GET TOTAL REVENUE (For Admin Dashboard)
    public BigDecimal getTotalRevenue() {
        BigDecimal total = BigDecimal.ZERO;
        String query = "SELECT SUM(amount) FROM payments WHERE payment_status = 'PAID'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return total == null ? BigDecimal.ZERO : total;
    }

    // 3. GET ALL PAYMENTS (For Admin to see every transaction)
    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String query = "SELECT * FROM payments ORDER BY payment_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToPayment(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 4. GET PAYMENTS FOR A SPECIFIC USER (Using SQL JOIN)
    public List<Payment> getPaymentsByUserId(int userId) {
        List<Payment> list = new ArrayList<>();
        // Logic: Look at payments, but JOIN with bookings to find which user they belong to
        String query = "SELECT p.* FROM payments p " +
                "JOIN bookings b ON p.booking_id = b.id " +
                "WHERE b.user_id = ? ORDER BY p.payment_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToPayment(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Helper method to convert a Database Row into a Java Payment Object
    private Payment mapRowToPayment(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setId(rs.getInt("id"));
        p.setBookingId(rs.getInt("booking_id"));
        p.setAmount(rs.getBigDecimal("amount"));
        p.setStatus(rs.getString("payment_status"));
        p.setPaymentDate(rs.getTimestamp("payment_date"));
        return p;
    }
}