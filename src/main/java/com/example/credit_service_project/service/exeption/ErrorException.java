package com.example.credit_service_project.service.exeption;

public class ErrorException extends RuntimeException {
    public ErrorException(String message) {
        super(message);
    }
}
