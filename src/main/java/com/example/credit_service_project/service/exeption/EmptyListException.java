package com.example.credit_service_project.service.exeption;

public class EmptyListException extends RuntimeException{
    public EmptyListException(String message) {
        super(message);
    }
}
