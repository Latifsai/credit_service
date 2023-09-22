package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.utils.AgreementUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchAgreementService {

    private final AgreementRepository repository;
    private final AgreementUtil util;

    /**
     * Here will be found an Agreement by ID and convert to response
     * @param id UUID
     * @return AgreementResponse
     */
    @Transactional(readOnly = true)
    public AgreementResponse searchAgreement(UUID id) {
        Agreement agreement = findById(id);
        log.info("Search agreement with ID: {}", agreement.getId());
        return util.convertToResponse(agreement);
    }

    /**
     * In this method will be found an Agreement by ID, if nothing was found that will handle the NotFoundException
     * @param id UUID
     * @return Agreement
     */
    public Agreement findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_AGREEMENT_MESSAGE));
    }
}
