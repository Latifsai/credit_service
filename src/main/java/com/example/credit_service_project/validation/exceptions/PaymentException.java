package com.example.credit_service_project.validation.exceptions;

public class PaymentException extends RuntimeException{
    public PaymentException(String message) {
        super(message);
    }
}
