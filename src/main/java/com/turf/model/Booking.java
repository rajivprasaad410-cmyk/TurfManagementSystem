package com.turf.model;

import java.sql.Date; // Specific SQL Date type
import java.sql.Time; // Specific SQL Time type
import java.math.BigDecimal; // For precise money calculations

public class Booking {
    // These variables directly map to the 'bookings' table columns
    private int id;
    private int userId; // This is our Foreign Key to the User table
    private Date bookingDate;
    private Time startTime;
    private Time endTime;
    private BigDecimal totalPrice; // Using BigDecimal for money is best practice
    private String status;

    // 1. Default Constructor
    // Why: Essential for Java to create an empty Booking object when fetching data.
    public Booking() {}

    // 2. Full Constructor
    // Why: To create a Booking object with all its details at once.
    public Booking(int id, int userId, Date bookingDate, Time startTime, Time endTime, BigDecimal totalPrice, String status) {
        this.id = id;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // 3. Constructor without ID (for new bookings, as ID is AUTO_INCREMENT)
    public Booking(int userId, Date bookingDate, Time startTime, Time endTime, BigDecimal totalPrice, String status) {
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // 4. Getters and Setters
    // Why: To encapsulate and safely access/modify the private fields.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 5. toString Method
    // Why: For easy debugging and printing Booking object details.
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookingDate=" + bookingDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}