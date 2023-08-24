package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.services.agreement.CreateAgreementServiceImp;
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

    private final CreateAgreementServiceImp create;
    private final GetAllAgreementsService get;
    private final SearchAgreementServiceImp search;


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AgreementResponse> findAll() {
        return get.execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AgreementResponse findAll(@PathVariable(name = "id") UUID id) {
        return search.execute(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementResponse create(@RequestBody CreateAgreementRequest request) {
        return create.execute(request);
    }
}
