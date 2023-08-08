package com.example.credit_service_project.service.errors.exceptions;

public class NearestPaymentNotFoundException extends RuntimeException {
    public NearestPaymentNotFoundException(String message) {
        super(message);
    }
}
