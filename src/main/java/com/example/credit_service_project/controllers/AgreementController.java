package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.agreement.GetAllAgreementsService;
import com.example.credit_service_project.services.agreement.SearchAgreementServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agreement")
public class AgreementController {

    private final AgreementCreateService create;
    private final GetAllAgreementsService get;
    private final SearchAgreementServiceImp search;


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AgreementResponse> findAll() {
        return get.getAllAgreements();
    }

    @GetMapping("/{id}") // модет юзер и админ
    @ResponseStatus(HttpStatus.FOUND)
    public AgreementResponse findAll(@PathVariable(name = "id") UUID id) {
        return search.searchAgreement(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementResponse create(@RequestBody CreateAgreementRequest request) {
        return create.createAgreement(request);
    }
}
