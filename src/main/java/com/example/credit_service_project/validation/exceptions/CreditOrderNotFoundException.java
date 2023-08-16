package com.example.credit_service_project.validation.exceptions;

public class CreditOrderNotFoundException extends RuntimeException {
    public CreditOrderNotFoundException(String message) {
        super(message);
    }
}
