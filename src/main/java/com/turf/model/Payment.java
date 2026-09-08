package com.turf.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private int id;
    private int bookingId;
    private BigDecimal amount;
    private String status;
    private Timestamp paymentDate;

    // Default Constructor
    public Payment() {}

    // Constructor for new payments (ID and Timestamp are handled by DB)
    public Payment(int bookingId, BigDecimal amount, String status) {
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Timestamp paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", bookingId=" + bookingId + ", amount=" + amount + ", status='" + status + '\'' + ", date=" + paymentDate + '}';
    }
}