package com.example.VisitorManagementSystem.exceptions;

@SuppressWarnings("ALL")
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
