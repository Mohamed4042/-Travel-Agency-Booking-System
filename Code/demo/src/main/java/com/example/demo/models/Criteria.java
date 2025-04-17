package com.example.demo.models;

public class Criteria {
    private String city;
    private String checkInDate;
    private String checkOutDate;
    private String roomType; // Optional, can be null if not specified
    private int numberOfGuests; // Optional, can be null if not specified

    // Getters and setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }
}
