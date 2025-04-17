package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields
public class Hotel {
    private int id;
    private String name;
    private String city;
    private String roomType;
    private double pricePerNight;
    private List<Room> rooms;

    // No-argument constructor
    public Hotel() {
    }

    @JsonCreator
    public Hotel(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("city") String city,
                 @JsonProperty("roomType") String roomType,
                 @JsonProperty("pricePerNight") double pricePerNight,
                 @JsonProperty("rooms") List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.rooms = rooms;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
