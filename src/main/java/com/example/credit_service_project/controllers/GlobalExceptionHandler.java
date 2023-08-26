package com.example.credit_service_project.controllers;

import com.example.credit_service_project.validation.ErrorException;
import com.example.credit_service_project.validation.ExceptionResponse;
import com.example.credit_service_project.validation.exceptions.*;
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

    @ExceptionHandler(OperationException.class)
    public ResponseEntity<?> handleOperationException(OperationException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(NotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CurrencyException.class)
    public ResponseEntity<?> handleProductNotFoundException(CurrencyException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AgreementException.class)
    public ResponseEntity<?> handleProductNotFoundException(AgreementException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<?> handleProductNotFoundException(AccountStatusException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EarlyPaymentException.class)
    public ResponseEntity<?> handleProductNotFoundException(EarlyPaymentException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handleProductNotFoundException(PaymentException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
