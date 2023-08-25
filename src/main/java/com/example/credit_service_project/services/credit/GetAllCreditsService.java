package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.services.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCreditsService {

    private final CreditRepository repository;
    private final CreditUtil util;

    @Transactional(readOnly = true)
    public List<CreditDTOResponse> getAllCredits() {
        return repository.findAll().stream()
                .map(util::convertToCreditResponse)
                .toList();
    }
}
