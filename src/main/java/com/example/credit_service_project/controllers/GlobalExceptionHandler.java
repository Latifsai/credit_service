package com.example.credit_service_project.controllers;

import com.example.credit_service_project.service.errors.ErrorException;
import com.example.credit_service_project.service.errors.ExceptionResponse;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.errors.exceptions.OperationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleViolationException(ConstraintViolationException e) {
        List<ErrorException> errorExceptions = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage())
                .map(ErrorException::new)
                .toList();
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleViolationException(NotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<?> handleViolationException(OperationException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
