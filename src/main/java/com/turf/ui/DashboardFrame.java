package com.turf.ui;

import com.turf.dao.*;
import com.turf.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class DashboardFrame extends JFrame {
    private User currentUser;
    private BookingDAO bookingDAO = new BookingDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();
    private JTable table;
    private DefaultTableModel tableModel;

    public DashboardFrame(User user) {
        this.currentUser = user;
        setTitle("Turf Management Dashboard - " + user.getRole());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(44, 62, 80)); // Dark Professional Blue
        topPanel.setPreferredSize(new Dimension(900, 50));
        JLabel welcomeLabel = new JLabel("  Logged in as: " + user.getName() + " (" + user.getRole() + ")");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> { new LoginFrame(); dispose(); });
        topPanel.add(logoutBtn, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // TABLE
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // FOOTER
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(900, 70));
        if (user.getRole().equals("ADMIN")) setupAdminControls(bottomPanel);
        else setupCustomerControls(bottomPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        // INITIAL LOAD
        showBookings();

        setVisible(true);
    }

    private void setupAdminControls(JPanel panel) {
        JButton btnRevenue = new JButton("💰 View Revenue");
        JButton btnAllBookings = new JButton("📋 All Bookings");
        JButton btnAllPayments = new JButton("💳 Transaction History");

        btnRevenue.addActionListener(e -> JOptionPane.showMessageDialog(this, "Total Revenue: ₹" + paymentDAO.getTotalRevenue()));
        btnAllBookings.addActionListener(e -> showBookings());
        btnAllPayments.addActionListener(e -> showPayments());

        panel.add(btnRevenue); panel.add(btnAllBookings); panel.add(btnAllPayments);
    }

    private void setupCustomerControls(JPanel panel) {
        JButton btnNewBooking = new JButton("➕ Book New Slot");
        JButton btnMyBookings = new JButton("📋 My Bookings");
        JButton btnMyPayments = new JButton("🧾 My Payments");

        btnNewBooking.addActionListener(e -> openBookingDialog());
        btnMyBookings.addActionListener(e -> showBookings());
        btnMyPayments.addActionListener(e -> showPayments());

        panel.add(btnNewBooking); panel.add(btnMyBookings); panel.add(btnMyPayments);
    }

    private void showBookings() {
        String[] cols = {"ID", "Date", "Start", "End", "Price", "Status"};
        tableModel.setColumnIdentifiers(cols);
        tableModel.setRowCount(0);
        List<Booking> list = currentUser.getRole().equals("ADMIN") ? bookingDAO.getAllBookings() : bookingDAO.getBookingsByUserId(currentUser.getId());
        for (Booking b : list) {
            tableModel.addRow(new Object[]{b.getId(), b.getBookingDate(), b.getStartTime(), b.getEndTime(), b.getTotalPrice(), b.getStatus()});
        }
    }

    private void showPayments() {
        String[] cols = {"Payment ID", "Booking ID", "Amount Paid", "Status", "Date/Time"};
        tableModel.setColumnIdentifiers(cols);
        tableModel.setRowCount(0);
        List<Payment> list = currentUser.getRole().equals("ADMIN") ? paymentDAO.getAllPayments() : paymentDAO.getPaymentsByUserId(currentUser.getId());
        for (Payment p : list) {
            tableModel.addRow(new Object[]{p.getId(), p.getBookingId(), p.getAmount(), p.getStatus(), p.getPaymentDate()});
        }
    }

    private void openBookingDialog() {
        JDialog dialog = new JDialog(this, "New Booking", true);
        dialog.setSize(300, 300);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));
        dialog.setLocationRelativeTo(this);

        JTextField dF = new JTextField("2025-01-01");
        JTextField tF = new JTextField("10:00:00");

        dialog.add(new JLabel(" Date:")); dialog.add(dF);
        dialog.add(new JLabel(" Time:")); dialog.add(tF);
        dialog.add(new JLabel(" Price:")); dialog.add(new JLabel("₹700.00"));

        JButton confirm = new JButton("Confirm & Pay");
        confirm.addActionListener(e -> {
            try {
                Date d = Date.valueOf(dF.getText());
                if (d.toLocalDate().isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(dialog, "Cannot book in the past!"); return;
                }
                Time s = Time.valueOf(tF.getText());
                Time en = new Time(s.getTime() + 3600000);
                if (bookingDAO.isSlotAvailable(d, s, en)) {
                    int id = bookingDAO.addBooking(new Booking(currentUser.getId(), d, s, en, new BigDecimal("700.00"), "CONFIRMED"));
                    paymentDAO.processPayment(new Payment(id, new BigDecimal("700.00"), "PAID"));
                    JOptionPane.showMessageDialog(dialog, "Success!");
                    dialog.dispose();
                    showBookings();
                } else JOptionPane.showMessageDialog(dialog, "Slot Taken!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(dialog, "Invalid Format!"); }
        });
        dialog.add(new JLabel("")); dialog.add(confirm);
        dialog.setVisible(true);
    }
}