package com.example.demo.services;

import com.example.demo.models.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private List<Booking> bookings = new ArrayList<>();

    public Booking createBooking(Booking booking) {
        bookings.add(booking);
        return booking;
    }

    public void confirmBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                booking.setConfirmed(true);
                return;
            }
        }
        throw new RuntimeException("Booking not found");
    }

    public void cancelBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                bookings.remove(booking);
                return;
            }
        }
        throw new RuntimeException("Booking not found");
    }

    public Booking getBookingByUserId(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                return booking;
            }
        }
        throw new RuntimeException("Booking not found");
    }
    public List<Booking> getBookingsByUser(String userId) {
        List<Booking> userBookings = new ArrayList<>();
        int userIdInt = Integer.parseInt(userId); // Convert userId to int for comparison
        for (Booking booking : bookings) {
            if (booking.getUser().getId() == userIdInt) { // Compare primitive types with ==
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

}
