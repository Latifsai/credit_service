package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCreditService {

    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;
    public List<CreditOrderResponseDTO> execute() {
        return repository.findAll().stream()
                .map(creditOrder -> util.convertToResponse(creditOrder))
                .toList();
    }
}
