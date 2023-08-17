package com.example.credit_service_project.fabrics.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.service.AgreementService;
import com.example.credit_service_project.service.agreement.GetAllAgreementsService;

import java.util.UUID;

public interface AgreementFabric {
    AgreementService<AgreementResponse, CreateAgreementRequest> create();
    GetAllAgreementsService getAll();
    AgreementService<AgreementResponse, UUID> find();
}
