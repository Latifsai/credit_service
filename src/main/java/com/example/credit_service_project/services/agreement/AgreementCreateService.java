package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.services.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.services.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgreementCreateService {

    private final AgreementRepository repository;
    private final CreditOrderSearchService searchCreditOrderService;
    private final AgreementUtil util;

    public AgreementResponse createAgreement(CreateAgreementRequest request) {
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());
        Agreement agreement = util.convertCreateRequestToEntity(request, creditOrder);
        Agreement savedAgreement = saveAgreement(agreement);
        return util.convertToResponse(savedAgreement);
    }

    public Agreement saveAgreement(Agreement agreement) {
        return repository.save(agreement);
    }
}
