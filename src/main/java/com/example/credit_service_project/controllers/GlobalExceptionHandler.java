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

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<?> handleCardNotFoundException(CardNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> handleClientNotFoundException(ClientNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<?> handleManagerNotFoundException(ManagerNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity<?> handleOperationNotFoundException(OperationNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentScheduleNotFoundException.class)
    public ResponseEntity<?> handlePaymentScheduleNotFoundException(PaymentScheduleNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException(PaymentException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NearestPaymentNotFoundException.class)
    public ResponseEntity<?> handlePaymentNotFoundException(NearestPaymentNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e) {
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
}
