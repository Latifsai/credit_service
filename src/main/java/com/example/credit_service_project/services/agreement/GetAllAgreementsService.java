package com.example.credit_service_project.services.agreement;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.services.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAgreementsService {

    private final AgreementUtil util;
    private final AgreementRepository repository;

    @Transactional(readOnly = true)
    public List<AgreementResponse> getAllAgreements() {
        return repository.findAll().stream()
                .map(util::convertToResponse)
                .toList();
    }
}
