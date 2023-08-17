package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.fabrics.agreement.AgreementFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementFabricImp fabric;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AgreementResponse> findAll() {
        return fabric.getAll().execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AgreementResponse findAll(@PathVariable(name = "id") UUID id) {
        return fabric.find().execute(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementResponse create(@RequestBody CreateAgreementRequest request) {
        return fabric.create().execute(request);
    }
}
