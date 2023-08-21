package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.paymentDTO.*;
import com.example.credit_service_project.fabrics.payments.PaymentFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentFabricImp fabric;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public PaymentResponseDTO search(@RequestBody PaymentsBelongsToAccountRequest request) {
        return fabric.getNearestPayment().execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetBelongsPaymentsResponse get(@RequestBody PaymentsBelongsToAccountRequest request) {
        return fabric.getBelongsPayments().execute(request);

    }

}
