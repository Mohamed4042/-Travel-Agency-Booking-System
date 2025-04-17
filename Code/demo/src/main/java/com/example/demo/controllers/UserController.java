package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.services.NotificationService;
import com.example.demo.services.NotificationLogger; // Assuming NotificationLogger is the observer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationLogger notificationLogger; // Observer for logging notifications

    // Register a new user
    @PostMapping("/register")
    public User register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        // Check if email is already registered


        // Register the user
        User newUser = userService.registerUser(name, email, password);

        // Add the observer (NotificationLogger) to the NotificationService
        notificationService.addObserver(notificationLogger);

        // Automatically generate a registration notification
        notificationService.generateNotification("REGISTER", email, name);

        return newUser;
    }

    // Login a user
    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        // Log in the user
        User loggedInUser = userService.loginUser(email, password);

        if (loggedInUser == null) {
            throw new RuntimeException("Invalid login credentials");
        }

        // Add the observer (NotificationLogger) to the NotificationService
        notificationService.addObserver(notificationLogger);

        // Automatically generate a login notification
        notificationService.generateNotification("LOGIN", email, loggedInUser.getName());

        return loggedInUser;
    }

    // Log out the user
    @PostMapping("/logout")
    public String logout() {
        // Log out the user
        userService.logoutUser();
        return "User logged out successfully";
    }
}
