package com.example.credit_service_project.controller;

import com.example.credit_service_project.validation.ErrorException;
import com.example.credit_service_project.validation.ExceptionResponse;
import com.example.credit_service_project.validation.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CurrencyException.class)
    public ResponseEntity<?> handleCurrencyException(CurrencyException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AgreementException.class)
    public ResponseEntity<?> handleAgreementException(AgreementException e) {
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
    public ResponseEntity<?> handleEarlyPaymentException(EarlyPaymentException e) {
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
    @ExceptionHandler(IsAlreadyExistException.class)
    public ResponseEntity<?> handleIsAlreadyExistException(IsAlreadyExistException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<?> handleInvalidJetException(InvalidJwtException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
        List<ErrorException> errorExceptions = List.of(new ErrorException(e.getMessage()));
        ExceptionResponse response = new ExceptionResponse(errorExceptions);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
