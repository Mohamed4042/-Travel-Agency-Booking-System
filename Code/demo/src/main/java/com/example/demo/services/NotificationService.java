package com.example.demo.services;

import com.example.demo.models.Notification;
import com.example.demo.models.Template;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {
    private Queue<Notification> notificationQueue = new LinkedList<>();
    private Map<String, Integer> recipientCount = new HashMap<>();
    private Map<String, Integer> templateCount = new HashMap<>();
    private int totalSent = 0;
    private int totalFailed = 0;
    private List<String> failureReasons = new ArrayList<>();
    private int totalGenerated = 0;  // Counter for generated notifications
    private List<NotificationObserver> observers = new ArrayList<>(); // List of observers

    // Automatically generate notifications based on user actions
    public void generateNotification(String action, String recipient, String additionalInfo) {
        Template template = createTemplate(action, additionalInfo);
        String message = "Subject: " + template.getSubject() + "\nBody: " + template.getBody();
        Notification notification = new Notification(recipient, message);
        notificationQueue.add(notification);

        // Track template usage
        templateCount.put(template.getSubject(), templateCount.getOrDefault(template.getSubject(), 0) + 1);
        totalGenerated++;

        // Notify observers about the new notification
        notifyObservers("New notification generated for recipient: " + recipient);

        // Send the notification
        sendNotification(recipient, message);  // This will increment totalSent, recipientCount, etc.
    }

    // Method to register an observer
    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    // Method to remove an observer
    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    // Method to notify all observers
    private void notifyObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }

    // Simulate sending a notification and updating statistics
    public void sendNotification(String recipient, String message) {
        try {
            System.out.println("Sending notification to " + recipient + ": " + message);
            totalSent++;
            recipientCount.put(recipient, recipientCount.getOrDefault(recipient, 0) + 1);
        } catch (Exception e) {
            totalFailed++;
            failureReasons.add("Failed to send to " + recipient + ": " + e.getMessage());
        }
    }

    // Helper method to create a Template based on action type
    private Template createTemplate(String action, String additionalInfo) {
        switch (action) {
            case "LOGIN":
                return new Template("1", "Welcome back!", "Welcome back, " + additionalInfo + "!", List.of("username"), "en");
            case "REGISTER":
                return new Template("2", "Thank you for registering!", "Thank you for registering, " + additionalInfo + "!", List.of("username"), "en");
            case "BOOK":
                return new Template("3", "Booking Confirmation", "Your booking for " + additionalInfo + " has been successfully placed.", List.of("bookingDetails"), "en");
            case "CANCEL":
                return new Template("4", "Booking Cancellation", "Your booking for " + additionalInfo + " has been cancelled.", List.of("bookingDetails"), "en");
            case "CONFIRM":
                return new Template("5", "Booking Confirmation", "Your booking for " + additionalInfo + " has been confirmed.", List.of("bookingDetails"), "en");
            case "EVENT_RECOMMENDATION":
                return new Template("6", "Event Recommendation", "We recommend the following event: " + additionalInfo, List.of("eventDetails"), "en");
            default:
                return new Template("7", "Action Performed", "Action performed: " + action, List.of("action"), "en");
        }
    }

    // Getters for statistics
    public Queue<Notification> getAllNotifications() {
        return notificationQueue;
    }

    public int getTotalSent() {
        return totalSent;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public List<String> getFailureReasons() {
        return failureReasons;
    }

    public Map<String, Integer> getRecipientCount() {
        return recipientCount;
    }

    public Map<String, Integer> getTemplateCount() {
        return templateCount;
    }

    public int getTotalGenerated() {
        return totalGenerated;
    }
}
