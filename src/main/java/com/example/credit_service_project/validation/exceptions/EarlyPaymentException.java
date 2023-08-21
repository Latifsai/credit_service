package com.example.credit_service_project.validation.exceptions;

public class EarlyPaymentException extends RuntimeException {
    public EarlyPaymentException(String message) {
        super(message);
    }
}
