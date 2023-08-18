package com.example.credit_service_project.service.cerdit;

import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.service.utils.CreditUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCreditsService {

    private final CreditRepository repository;
    private final CreditUtil util;
    public List<CreditDTOResponse> execute () {
        return repository.findAll().stream()
                .map(util::convertToCreditResponse)
                .toList();
    }
}
