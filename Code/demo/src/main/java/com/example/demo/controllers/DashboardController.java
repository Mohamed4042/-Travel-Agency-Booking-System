package com.example.demo.controllers;

import com.example.demo.models.Dashboard;
import com.example.demo.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Get dashboard data, including notifications, booking details, and recommended events
    @GetMapping
    public Dashboard getDashboardData(@RequestParam String userId) {
        return dashboardService.getDashboardData(userId);
    }
}
