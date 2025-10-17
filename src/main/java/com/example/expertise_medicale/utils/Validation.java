package com.example.expertise_medicale.utils;

public class Validation {

    public static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isEmail(String email) {
        return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isNumeric(String value) {
        return value != null && value.matches("\\d+");
    }
}
