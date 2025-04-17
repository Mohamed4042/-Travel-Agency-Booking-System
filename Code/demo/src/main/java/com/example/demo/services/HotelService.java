package com.example.demo.services;

import com.example.demo.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService {

    private final RestTemplate restTemplate;
    private List<Hotel> hotels;

    public HotelService() {
        this.restTemplate = new RestTemplate();
        this.hotels = new ArrayList<>();
    }

    // 🛠️ Fetch hotels from external API on startup
    @EventListener(ContextRefreshedEvent.class)
    public void fetchHotelsFromAPI() {
        String apiUrl = "https://c8f8f708-1aae-4e0b-b5b4-c6688a2fd628.mock.pstmn.io/hotels"; // Replace with your real API URL

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
                // Convert the response body to JSON (Hotel[] array) using ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                Hotel[] fetchedHotels = objectMapper.readValue(response.getBody(), Hotel[].class);
                if (fetchedHotels != null) {
                    hotels = new ArrayList<>(Arrays.asList(fetchedHotels));
                    System.out.println("✅ Hotels successfully fetched from external API.");
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to fetch hotels from external API: " + e.getMessage());
        }
    }

    // 🏨 Manually initialize hotels if API fails
    public void initializeLocalHotels() {
        List<Room> cairoMarriottRooms = new ArrayList<>();
        cairoMarriottRooms.add(new Room(1, RoomType.valueOf("SINGLE"), 2, 100));
        cairoMarriottRooms.add(new Room(2, RoomType.valueOf("DOUBLE"), 2, 150));
        hotels.add(new Hotel(1, "Cairo Marriott", "Cairo", "Single", 100, cairoMarriottRooms));

        List<Room> hiltonRamsesRooms = new ArrayList<>();
        hiltonRamsesRooms.add(new Room(3, RoomType.valueOf("SINGLE"), 1, 120));
        hiltonRamsesRooms.add(new Room(4, RoomType.valueOf("DOUBLE"), 2, 180));
        hotels.add(new Hotel(2, "Hilton Ramses", "Cairo", "Double", 150, hiltonRamsesRooms));

        List<Room> gizaPyramidsViewRooms = new ArrayList<>();
        gizaPyramidsViewRooms.add(new Room(5, RoomType.valueOf("FAMILY"), 4, 250));
        hotels.add(new Hotel(3, "Giza Pyramids View", "Giza", "Family", 200, gizaPyramidsViewRooms));

        System.out.println("✅ Local hotels initialized as fallback.");
    }

    // 📄 Fetch all hotels
    public List<Hotel> getAllHotels() {
        return hotels;
    }

    // 🔍 Find a hotel by ID without using `.filter`
    public Hotel getHotelById(int id) {
        for (Hotel hotel : hotels) {
            if (hotel.getId() == id) {
                return hotel;
            }
        }
        throw new RuntimeException("Hotel not found");
    }

    // 🔎 Search hotels by city without using `.filter`
    public List<Hotel> searchHotels(String city) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(city)) {
                result.add(hotel);
            }
        }
        return result;
    }

    // ➕ Add a new hotel
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    // 🛏️ Search for rooms based on criteria without using `.filter`
    public List<Room> lookForRooms(Criteria criteria) {
        List<Room> availableRooms = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (hotel.getCity().equalsIgnoreCase(criteria.getCity())) {
                for (Room room : hotel.getRooms()) {
                    if ((criteria.getRoomType() == null || room.getRoomType().toString().equalsIgnoreCase(criteria.getRoomType())) &&
                            (room.getCapacity() >= criteria.getNumberOfGuests())) {
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }
    public List<Room> lookForRooms(Hotel hotel, RoomType roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.getRoomType().equals(roomType)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public Hotel findHotelByName(String hotelName) {
        // Fetch all available hotels (replace this with your actual data-fetching logic)
        List<Hotel> allHotels = getAllHotels(); // Ensure you have this method or replace it with the correct one

        // Loop through the list to find the hotel by name
        for (Hotel hotel : allHotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }

        // Return null if no hotel matches
        return null;
    }

    public void removeBookedRoom(String hotelName, RoomType roomType) {
        // Find the hotel by name
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            // Remove the room with the matching RoomType
            hotel.getRooms().removeIf(room -> room.getRoomType() == roomType);
        }
    }




}
