package com.example.demo.controllers;

import com.example.demo.models.Event;
import com.example.demo.models.Hotel;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    @GetMapping("/search")
    public List<Event> searchHotels(@RequestParam String city) {
        return eventService.searchEvents(city);
    }

}
