package com.example.demo.services;

import com.example.demo.models.Booking;
import com.example.demo.models.Dashboard;
import com.example.demo.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class DashboardService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BookingService bookingService; // Inject BookingService

    public Dashboard getDashboardData(String userId) {
        // Get notifications for the user
        Queue<Notification> notifications = notificationService.getAllNotifications();

        // Get all bookings for the user and find confirmed ones
        List<Booking> allBookings = bookingService.getBookingsByUser(userId);
        List<Booking> confirmedBookings = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (booking.isConfirmed()) {
                confirmedBookings.add(booking);
            }
        }

        // Create Dashboard and set notifications and confirmed bookings
        Dashboard dashboard = new Dashboard();
        dashboard.setNotifications(notifications);
        dashboard.setConfirmedBookings(confirmedBookings);

        return dashboard;
    }
}
