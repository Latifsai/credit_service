package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.services.paymentSchedule.GetBelongsToTheAccountPaymentsListServiceImp;
import com.example.credit_service_project.services.paymentSchedule.GetNearestPaymentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final GetBelongsToTheAccountPaymentsListServiceImp getBelongsToTheAccountPaymentsList;
    private final GetNearestPaymentServiceImp getNearestPayment;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public PaymentResponseDTO search(@RequestBody PaymentsBelongsToAccountRequest request) {
        return getNearestPayment.execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetBelongsPaymentsResponse get(@RequestBody PaymentsBelongsToAccountRequest request) {
        return getBelongsToTheAccountPaymentsList.execute(request);
    }

}
