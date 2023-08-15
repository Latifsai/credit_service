package com.example.credit_service_project.validation.exceptions;

public class NearestPaymentNotFoundException extends RuntimeException {
    public NearestPaymentNotFoundException(String message) {
        super(message);
    }
}
