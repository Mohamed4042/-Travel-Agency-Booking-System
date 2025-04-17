package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    public User registerUser(String name, String email, String password) {
        User newUser = new User(users.size() + 1, name, email, password);
        users.add(newUser);
        return newUser;
    }

    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;  // Set logged-in user
                return user;
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    public User getLoggedInUser() {
        if (loggedInUser == null) {
            throw new RuntimeException("No user is logged in");
        }
        return loggedInUser;
    }

    public void logoutUser() {
        loggedInUser = null;
    }
}
