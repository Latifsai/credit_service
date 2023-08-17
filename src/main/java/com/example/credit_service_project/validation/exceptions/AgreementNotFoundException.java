package com.example.credit_service_project.validation.exceptions;

public class AgreementNotFoundException extends RuntimeException {
    public AgreementNotFoundException(String message) {
        super(message);
    }
}
