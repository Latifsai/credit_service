package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.agreement.GetAllAgreementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agreement")
public class AgreementController {

    private final AgreementCreateService create;
    private final GetAllAgreementsService get;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AgreementResponse> findAll() {
        return get.getAllAgreements();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementResponse create(@RequestBody CreateAgreementRequest request) {
        return create.createAgreement(request);
    }
}
