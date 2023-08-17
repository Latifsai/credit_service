package com.example.credit_service_project.fabrics.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.AgreementService;
import com.example.credit_service_project.service.agreement.CreateAgreementServiceImp;
import com.example.credit_service_project.service.agreement.GetAllAgreementsService;
import com.example.credit_service_project.service.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.service.creditOrder.SearchCreditOrderServiceImp;
import com.example.credit_service_project.service.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgreementFabricImp implements AgreementFabric {

    private final AgreementRepository repository;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final AgreementUtil util;

    @Override
    public AgreementService<AgreementResponse, CreateAgreementRequest> create() {
        return new CreateAgreementServiceImp(repository, searchCreditOrderService, util);
    }

    @Override
    public GetAllAgreementsService getAll() {
        return new GetAllAgreementsService(util, repository);
    }

    @Override
    public AgreementService<AgreementResponse, UUID> find() {
        return new SearchAgreementServiceImp(repository, util);
    }
}
