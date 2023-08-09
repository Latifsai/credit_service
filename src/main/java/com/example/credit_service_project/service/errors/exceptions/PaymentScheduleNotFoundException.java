package com.example.credit_service_project.service.errors.exceptions;

public class PaymentScheduleNotFoundException extends RuntimeException {
    public PaymentScheduleNotFoundException(String message) {
        super(message);
    }
}
