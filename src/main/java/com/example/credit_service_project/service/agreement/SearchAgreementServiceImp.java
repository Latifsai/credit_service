package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.AgreementService;
import com.example.credit_service_project.service.utils.AgreementUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AgreementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchAgreementServiceImp implements AgreementService<AgreementResponse, UUID> {

    private final AgreementRepository repository;
    private final AgreementUtil util;

    @Override
    public AgreementResponse execute(UUID id) {
        Agreement agreement = repository.findById(id)
                .orElseThrow(() -> new AgreementNotFoundException(ErrorsMessage.NOT_FOUND_AGREEMENT_MESSAGE));
        return util.convertToResponse(agreement);
    }
}
