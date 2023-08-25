package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.services.utils.AgreementUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchAgreementServiceImp {

    private final AgreementRepository repository;
    private final AgreementUtil util;

    @Transactional(readOnly = true)
    public AgreementResponse searchAgreement(UUID id) {
        Agreement agreement = findById(id);
        return util.convertToResponse(agreement);
    }

    public Agreement findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_AGREEMENT_MESSAGE));
    }
}
