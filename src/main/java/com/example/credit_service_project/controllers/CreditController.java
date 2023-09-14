package com.example.credit_service_project.controllers;

import com.example.credit_service_project.dto.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.dto.creditDTO.CreditResponseDTO;
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
    public List<CreditResponseDTO> get() {
        return getAllCredits.getAllCredits();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCreditDTOResponse create(@RequestBody CreateCreditRequestDTO request) {
        return create.createCredit(request);
    }

}
