package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCreditOrdersService {

    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Transactional(readOnly = true)
    public List<CreditOrderResponseDTO> getAllCreditOrders() {
        return getOrders().stream()
                .map(util::convertToResponse)
                .toList();
    }

    public List<CreditOrder> getOrders() {
        return repository.findAll();
    }
}
