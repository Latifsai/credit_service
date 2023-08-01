package com.example.credit_service_project.service.errors.exceptions;

public class OperationException extends RuntimeException{
    public OperationException(String message) {
        super(message);
    }
}
