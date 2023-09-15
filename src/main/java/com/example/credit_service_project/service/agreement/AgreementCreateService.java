package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.service.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementCreateService {

    private final AgreementRepository repository;
    private final CreditOrderSearchService searchCreditOrderService;
    private final AgreementUtil util;

    public AgreementResponse createAgreement(CreateAgreementRequest request) {
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getCreditOrderID());
        Agreement agreement = util.convertCreateRequestToEntity(request, creditOrder);
        Agreement savedAgreement = saveAgreement(agreement);
        log.info("Create agreement with number: {}", savedAgreement.getNumber());
        return util.convertToResponse(savedAgreement);
    }

    public Agreement saveAgreement(Agreement agreement) {
        return repository.save(agreement);
    }
}
