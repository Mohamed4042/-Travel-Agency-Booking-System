package com.example.demo.models;

import java.util.List;
import java.util.Queue;

public class Dashboard {

    private Queue<Notification> notifications;
    private List<Booking> confirmedBookings; // Add confirmed bookings to the dashboard

    // Getter and setter for notifications
    public Queue<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Queue<Notification> notifications) {
        this.notifications = notifications;
    }

    // Getter and setter for confirmed bookings
    public List<Booking> getConfirmedBookings() {
        return confirmedBookings;
    }

    public void setConfirmedBookings(List<Booking> confirmedBookings) {
        this.confirmedBookings = confirmedBookings;
    }
}
