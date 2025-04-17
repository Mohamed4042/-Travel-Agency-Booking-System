package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;

    // Create a booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Get the logged-in user
        User loggedInUser = userService.getLoggedInUser();  // Throws exception if no user is logged in

        // Associate the logged-in user with the booking
        booking.setUser(loggedInUser);

        // Handle hotel booking scenario
        if ("hotel".equalsIgnoreCase(booking.getServiceBooked())) {
            // Ensure room type is set
            if (booking.getRoomType() == null) {
                throw new RuntimeException("Room type is required for hotel bookings");
            }

            // Search for hotels in the specified city
            List<Hotel> availableHotels = hotelService.searchHotels(booking.getCity());

            // Check if the specified hotel is available in the city
            Hotel selectedHotel = null;
            for (Hotel hotel : availableHotels) {
                if (hotel.getName().equalsIgnoreCase(booking.getHotelName())) {
                    selectedHotel = hotel;
                    break;
                }
            }

            if (selectedHotel == null) {
                throw new RuntimeException("Hotel not found in the specified city");
            }

            // Check for available rooms in the selected hotel
            List<Room> availableRooms = hotelService.lookForRooms(selectedHotel, booking.getRoomType());
            if (availableRooms.isEmpty()) {
                throw new RuntimeException("No rooms available in the specified hotel with the chosen room type");
            }

            // Proceed with the booking
            booking.setRoomType(selectedHotel.getRooms().get(0).getRoomType()); // Assign the room type from the hotel (or chosen room)

        }

        // Proceed to create the booking
        Booking createdBooking = bookingService.createBooking(booking);

        // Automatically generate a booking creation notification
        notificationService.generateNotification("BOOK", loggedInUser.getEmail(), booking.getServiceBooked());

        return createdBooking;
    }

    @PostMapping("/{bookingId}/confirm")
    public void confirmBooking(@PathVariable String bookingId) {
        bookingService.confirmBooking(bookingId);

        // Retrieve the booking and generate a confirmation notification
        Booking confirmedBooking = bookingService.getBookingByUserId(bookingId);
        notificationService.generateNotification("CONFIRM", confirmedBooking.getUser().getEmail(), confirmedBooking.getServiceBooked());

        if ("hotel".equalsIgnoreCase(confirmedBooking.getServiceBooked())) {
            // Remove the booked room from availability
            hotelService.removeBookedRoom(confirmedBooking.getHotelName(), confirmedBooking.getRoomType());
        }

        if ("event".equalsIgnoreCase(confirmedBooking.getServiceBooked())) {
            // Retrieve the event by name from the correct field, not hotelName
            String eventName = confirmedBooking.getEventName(); // Replace with the correct field
            if (eventName != null) {
                // Retrieve the event by name
                Event event = eventService.findEventByName(eventName);

                if (event != null) {
                    // Deduct tickets for the event
                    eventService.deductTickets(event.getName(), 1); // Pass the event name and tickets to deduct
                } else {
                    throw new RuntimeException("Event not found: " + eventName);
                }
            } else {
                throw new RuntimeException("Event name is missing in the booking");
            }
        }

        List<Event> recommendedEvents = eventService.recommendEvents(confirmedBooking);

        // Generate notifications for the recommended events with detailed information
        for (Event event : recommendedEvents) {
            String eventDetails = "Event: " + event.getName() +
                    " from " + event.getStartDate() +
                    " to " + event.getEndDate();
            notificationService.generateNotification("EVENT_RECOMMENDATION", confirmedBooking.getUser().getEmail(), eventDetails);
        }
    }



    // Cancel booking
    @PostMapping("/{bookingId}/cancel")
    public void cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);

        // Retrieve the booking and generate a cancellation notification
        Booking cancelledBooking = bookingService.getBookingByUserId(bookingId);
        notificationService.generateNotification("CANCEL", cancelledBooking.getUser().getEmail(), cancelledBooking.getServiceBooked());
    }
}
