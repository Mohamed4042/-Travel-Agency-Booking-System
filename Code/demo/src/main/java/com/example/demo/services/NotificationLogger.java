package com.example.demo.services;
import org.springframework.stereotype.Service;
@Service
public class NotificationLogger implements NotificationObserver {
    @Override
    public void update(String message) {
        // Log the notification update
        System.out.println("Notification Update: " + message);
    }
}
