package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore any unknown fields
public class Event {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private String city;   // Add city field to Event model
    private int availableTickets; // New field for available tickets

    // No-argument constructor
    public Event() {
    }

    @JsonCreator
    public Event(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("startDate") String startDate,
                 @JsonProperty("endDate") String endDate,
                 @JsonProperty("city") String city,
                 @JsonProperty("availableTickets") int availableTickets) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.availableTickets = availableTickets != 0 ? availableTickets : 100;  // Set a fallback default value
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
