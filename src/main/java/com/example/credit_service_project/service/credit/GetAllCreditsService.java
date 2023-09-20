package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.dto.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCreditsService {

    private final CreditRepository repository;
    private final CreditUtil util;

    @Transactional(readOnly = true)
    public List<CreditResponseDTO> getAllCredits() {
        log.info("Get a list of credits");
        return repository.findAll().stream()
                .map(util::convertToCreditResponse)
                .collect(Collectors.toList());
    }
}
