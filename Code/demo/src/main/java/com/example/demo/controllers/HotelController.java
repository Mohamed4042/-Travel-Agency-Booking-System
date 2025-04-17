package com.example.demo.controllers;

import com.example.demo.models.Criteria;
import com.example.demo.models.Hotel;
import com.example.demo.models.Room;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // Search hotels by city
    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String city) {
        return hotelService.searchHotels(city);
    }

    // Get all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Get hotel details by ID
    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable int id) {
        return hotelService.getHotelById(id);
    }

    // Add a new hotel (for admin)
    @PostMapping
    public void addHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
    }
    @PostMapping("/searchRooms")
    public List<Room> lookForRooms(@RequestBody Criteria criteria) {
        return hotelService.lookForRooms(criteria);
    }
}
