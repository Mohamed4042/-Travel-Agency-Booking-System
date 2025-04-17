package com.example.demo.controllers;

import com.example.demo.models.NotificationStatistics;
import com.example.demo.services.NotificationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-statistics")
public class NotificationStatisticsController {
    @Autowired
    private NotificationStatisticsService notificationStatisticsService;

    // Generate notification statistics report
    @GetMapping
    public NotificationStatistics generateStatistics() {
        return notificationStatisticsService.generateStatistics();
    }
}
