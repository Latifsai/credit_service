package com.example.credit_service_project.service.agreement;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.repository.AgreementRepository;
import com.example.credit_service_project.service.utils.AgreementUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllAgreementsService {

    private final AgreementUtil util;
    private final AgreementRepository repository;

    @Transactional(readOnly = true)
    public List<AgreementResponse> getAllAgreements() {
        log.info("Get a list of agreements");
        return repository.findAll().stream()
                .map(util::convertToResponse)
                .collect(Collectors.toList());

    }
}
