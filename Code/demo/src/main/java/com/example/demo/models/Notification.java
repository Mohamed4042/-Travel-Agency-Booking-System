package com.example.demo.models;

public class Notification {
    private String recipient;
    private String message;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    // Getters
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }

    // Utility method for sending notifications
    public void send() {
        // Logic to send the notification (e.g., via email or message queue)
        System.out.println("Notification sent to " + recipient + ": " + message);
    }
}
