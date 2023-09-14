package com.example.credit_service_project.controllers;

import com.example.credit_service_project.dto.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.services.paymentSchedule.GetBelongsToAccountPaymentsService;
import com.example.credit_service_project.services.paymentSchedule.GetNearestPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments") // manager and user
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final GetBelongsToAccountPaymentsService getBelongsToTheAccountPaymentsList;
    private final GetNearestPaymentService getNearestPayment;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public PaymentResponseDTO search(@RequestBody PaymentsBelongsToAccountRequest request) {
        return getNearestPayment.getNearestPayment(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public GetBelongsPaymentsResponse getBelongsPaymentsResponse(@RequestBody PaymentsBelongsToAccountRequest request) {
        return getBelongsToTheAccountPaymentsList.getBelongsToAccountPayments(request);
    }

}
