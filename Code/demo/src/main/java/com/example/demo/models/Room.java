package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private int id;
    private RoomType roomType;
    private int capacity;
    private double price;

    // No-argument constructor
    public Room() {
    }

    @JsonCreator
    public Room(@JsonProperty("id") int id,
                @JsonProperty("roomType") RoomType roomType,
                @JsonProperty("capacity") int capacity,
                @JsonProperty("price") double price) {
        this.id = id;
        this.roomType = roomType;
        this.capacity = capacity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
