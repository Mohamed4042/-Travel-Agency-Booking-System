package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Booking {
    private String id;
    private User user;
    private String serviceBooked;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date bookingDate;
    private boolean confirmed;
    private String city;  // Added city field
    private String hotelName; // Added hotel name for booking
    private RoomType roomType;  // Room type for hotel bookings
    private int numOfTickets;
    private String eventName;// Added number of tickets for events

    // Constructor
    public Booking(String id, User user, String serviceBooked, Date bookingDate, String city, String hotelName, RoomType roomType) {
        this.id = id;
        this.user = user;
        this.serviceBooked = serviceBooked;
        this.bookingDate = bookingDate;
        this.city = city;
        this.hotelName = hotelName; // Initialize hotel name
        this.roomType = roomType;   // Initialize room type
        this.confirmed = false;
    }
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getServiceBooked() {
        return serviceBooked;
    }

    public void setServiceBooked(String serviceBooked) {
        this.serviceBooked = serviceBooked;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    // Method to confirm the booking
    public void confirmBooking() {
        this.confirmed = true;
    }

    // Method to cancel the booking
    public void cancelBooking() {
        this.confirmed = false;
    }
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
