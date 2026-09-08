package com.turf.dao;

import com.turf.model.Booking;
import com.turf.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean isSlotAvailable(Date date, Time start, Time end) {
        String query = "SELECT count(*) FROM bookings WHERE booking_date = ? " +
                "AND status = 'CONFIRMED' " +
                "AND ((start_time < ? AND end_time > ?))";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, date);
            pstmt.setTime(2, end);
            pstmt.setTime(3, start);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) == 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public int addBooking(Booking booking) {
        String query = "INSERT INTO bookings (user_id, booking_date, start_time, end_time, total_price, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, booking.getUserId());
            pstmt.setDate(2, booking.getBookingDate());
            pstmt.setTime(3, booking.getStartTime());
            pstmt.setTime(4, booking.getEndTime());
            pstmt.setBigDecimal(5, booking.getTotalPrice());
            pstmt.setString(6, booking.getStatus());
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    // --- NEW: USER HISTORY METHOD ---
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToBooking(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // --- NEW: CANCELLATION METHOD ---
    public boolean cancelBooking(int bookingId) {
        String query = "UPDATE bookings SET status = 'CANCELLED' WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToBooking(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Helper to avoid repeating code
    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setBookingDate(rs.getDate("booking_date"));
        b.setStartTime(rs.getTime("start_time"));
        b.setEndTime(rs.getTime("end_time"));
        b.setTotalPrice(rs.getBigDecimal("total_price"));
        b.setStatus(rs.getString("status"));
        return b;
    }
}