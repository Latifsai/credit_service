package com.example.credit_service_project.validation.exceptions;

public class IsAlreadyExistException extends RuntimeException {
    public IsAlreadyExistException(String message) {
        super(message);
    }
}
