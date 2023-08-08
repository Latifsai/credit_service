package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.fabrics.payments.PaymentFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentFabricImp fabric;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody PaymentsBelongsToAccountRequest request) {
        var response = fabric.getNearestPayment().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestBody PaymentsBelongsToAccountRequest request) {
        var response = fabric.getBelongsPayments().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddPaymentRequestDTO request) {
        var response = fabric.addPaymentSchedule().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
