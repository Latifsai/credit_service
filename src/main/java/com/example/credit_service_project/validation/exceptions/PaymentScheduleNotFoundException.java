package com.example.credit_service_project.validation.exceptions;

public class PaymentScheduleNotFoundException extends RuntimeException {
    public PaymentScheduleNotFoundException(String message) {
        super(message);
    }
}
