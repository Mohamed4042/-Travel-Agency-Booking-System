package com.example.demo.services;

import com.example.demo.models.NotificationStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationStatisticsService {

    @Autowired
    private NotificationService notificationService;

    // Generate the notification statistics
    public NotificationStatistics generateStatistics() {
        // Get the statistics data from the NotificationService
        int totalSent = notificationService.getTotalSent();
        int totalFailed = notificationService.getTotalFailed();
        List<String> failureReasons = notificationService.getFailureReasons();
        Map<String, Integer> recipientCount = notificationService.getRecipientCount();
        Map<String, Integer> templateCount = notificationService.getTemplateCount();

        // Find the most notified recipient (the one with the highest count)
        String mostNotifiedRecipient = "None";
        int maxRecipientCount = 0;
        for (Map.Entry<String, Integer> entry : recipientCount.entrySet()) {
            if (entry.getValue() > maxRecipientCount) {
                mostNotifiedRecipient = entry.getKey();
                maxRecipientCount = entry.getValue();
            }
        }

        // Find the most used template (the one with the highest count)
        String mostUsedTemplate = "None";
        int maxTemplateCount = 0;
        for (Map.Entry<String, Integer> entry : templateCount.entrySet()) {
            if (entry.getValue() > maxTemplateCount) {
                mostUsedTemplate = entry.getKey();
                maxTemplateCount = entry.getValue();
            }
        }

        // Create and return NotificationStatistics object
        return new NotificationStatistics(totalSent, totalFailed, failureReasons, mostUsedTemplate);
    }
}
