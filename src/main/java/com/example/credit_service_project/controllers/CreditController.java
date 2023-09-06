package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreateCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.services.credit.CreditCreateService;
import com.example.credit_service_project.services.credit.GetAllCreditsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditCreateService create;
    private final GetAllCreditsService getAllCredits;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditDTOResponse> get() {
        return getAllCredits.getAllCredits();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCreditDTOResponse create(@RequestBody CreateCreditDTORequest request) {
        return create.createCredit(request);
    }

}
