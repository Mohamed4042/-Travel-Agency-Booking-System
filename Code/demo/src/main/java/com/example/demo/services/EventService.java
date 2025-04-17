package com.example.demo.services;

import com.example.demo.models.Booking;
import com.example.demo.models.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    private final RestTemplate restTemplate;
    private List<Event> events;
    private int nextEventId = 1; // To simulate unique IDs


    public EventService() {
        this.restTemplate = new RestTemplate();
        this.events = new ArrayList<>();
    }

    // 🛠️ Fetch events from external API on startup
    @EventListener(ContextRefreshedEvent.class)
    public void fetchEventsFromAPI() {
        String apiUrl = "https://ab8ed89c-a438-40eb-b146-70b45a772fb1.mock.pstmn.io/events"; // Replace with your real API URL

        try {
            // Set Accept header to request JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the API call with headers to accept JSON
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            // Check if the response is in HTML format (for error handling)
            if (response.getHeaders().getContentType().equals(MediaType.TEXT_HTML)) {
                System.err.println("❌ Received HTML instead of JSON. Response Body: " + response.getBody());
            } else {
                // Convert the response body to JSON (Event[] array) using ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                Event[] fetchedEvents = objectMapper.readValue(response.getBody(), Event[].class);
                if (fetchedEvents != null) {
                    events = new ArrayList<>(Arrays.asList(fetchedEvents));
                    System.out.println("✅ Events successfully fetched from external API.");
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to fetch events from external API: " + e.getMessage());
        }
    }



    // 📄 Fetch all events
    public List<Event> getAllEvents() {
        return events;
    }

    // 🔍 Find an event by ID without
    public Event getEventById(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        throw new RuntimeException("Event not found");
    }

    // 🔎 Search events by city
    public List<Event> searchEvents(String city) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getCity().equalsIgnoreCase(city)) {
                result.add(event);
            }
        }
        return result;
    }

    // ➕ Add a new event
    public void addEvent(Event event) {
        event.setId(nextEventId++); // Assign a new unique ID
        events.add(event);
    }

    // 📅 Search for events by date
    public List<Event> lookForEventsByDate(String date) {
        List<Event> availableEvents = new ArrayList<>();

        for (Event event : events) {
            if (event.getStartDate().equals(date) || event.getEndDate().equals(date)) {
                availableEvents.add(event);
            }
        }
        return availableEvents;
    }

    public List<Event> recommendEvents(Booking booking) {
        List<Event> recommendedEvents = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Get the start and end dates of the booking
        Date bookingStartDate = booking.getBookingDate();
        System.out.println("Booking Date: " + booking.getBookingDate());


        // Check if bookingStartDate is null
        if (bookingStartDate == null) {
            System.err.println("❌ Booking start date is null!");
            return recommendedEvents;  // Return empty list or handle as needed
        }

        Date bookingEndDate = new Date(bookingStartDate.getTime() + 86400000L); // Add one day to booking date to simulate one day booking

        String bookingCity = booking.getCity();  // Get the city from the booking

        for (Event event : events) {
            try {
                // Convert event dates from String to Date
                Date eventStartDate = dateFormat.parse(event.getStartDate());
                Date eventEndDate = dateFormat.parse(event.getEndDate());

                // Check if the event's city matches the booking city and if event dates overlap with booking dates
                boolean isCityMatch = event.getCity().equalsIgnoreCase(bookingCity);
                boolean isDateOverlap = (eventStartDate.before(bookingEndDate) && eventEndDate.after(bookingStartDate)) ||
                        eventStartDate.equals(bookingStartDate) || eventEndDate.equals(bookingEndDate);

                if (isCityMatch && isDateOverlap) {
                    recommendedEvents.add(event);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return recommendedEvents;
    }
    public Event findEventByName(String eventName) {
        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null; // Return null if the event is not found
    }


    public void deductTickets(String eventName, int numTickets) {
        Event event = findEventByName(eventName); // Modify this method to find event by name
        if (event != null && event.getAvailableTickets() >= numTickets) {
            event.setAvailableTickets(event.getAvailableTickets() - numTickets);
        } else {
            throw new RuntimeException("Not enough tickets available or event not found");
        }
    }



}
