package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.fabrics.credit.CreditFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    CreditFabricImp fabric;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditDTOResponse> get() {
        return fabric.get().execute();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCreditDTOResponse create(@RequestBody AddCreditDTORequest request) {
        return fabric.add().execute(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PaymentResponseDTO> getUnpaidPayments(@PathVariable("id") UUID id) {
        return fabric.getAllUnpaidPaymentsBelongsCreditService().execute(id);
    }

}
