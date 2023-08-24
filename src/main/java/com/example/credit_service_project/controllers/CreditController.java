package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.services.credit.CreateCreditServiceImp;
import com.example.credit_service_project.services.credit.GetAllCreditsService;
import com.example.credit_service_project.services.credit.GetAllUnpaidPaymentsBelongsCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreateCreditServiceImp create;
    private final GetAllCreditsService getAllCredits;
    private final GetAllUnpaidPaymentsBelongsCreditService getAllUnpaidPaymentsBelongsCreditService;


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditDTOResponse> get() {
        return getAllCredits.execute();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCreditDTOResponse create(@RequestBody AddCreditDTORequest request) {
        return create.execute(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PaymentResponseDTO> getUnpaidPayments(@PathVariable("id") UUID id) {
        return getAllUnpaidPaymentsBelongsCreditService.execute(id);
    }

}
