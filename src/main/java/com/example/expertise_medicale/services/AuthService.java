package com.example.expertise_medicale.services;

import com.example.expertise_medicale.models.User;

public class AuthService {
    private final UserService<User> userService;

    public AuthService(UserService<User> userService) {
        this.userService = userService;
    }

    public User login(String email, String password) {
        return userService.findByEmailPassword(email, password);
    }
}
