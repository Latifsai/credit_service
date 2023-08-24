package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.services.AgreementService;
import com.example.credit_service_project.services.creditOrder.SearchCreditOrderServiceImp;
import com.example.credit_service_project.services.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAgreementServiceImp implements AgreementService<AgreementResponse, CreateAgreementRequest> {

    private final AgreementRepository repository;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final AgreementUtil util;

    @Override
    public AgreementResponse execute(CreateAgreementRequest request) {
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());
        Agreement agreement = util.convertCreateRequestToEntity(request, creditOrder);
        saveAgreement(agreement);
        return util.convertToResponse(agreement);
    }

    public Agreement saveAgreement(Agreement agreement) {
        return repository.save(agreement);
    }
}
