package com.example.Biblioteka.exception;

public class ReviewerNotFoundException extends RuntimeException {
    public ReviewerNotFoundException(String message) {
        super(message);
    }
}
