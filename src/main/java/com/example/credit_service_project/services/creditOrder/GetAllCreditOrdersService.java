package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCreditOrdersService {

    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Transactional(readOnly = true)
    public List<CreditOrderResponseDTO> getAllCreditOrders() {
        log.info("Get a list of credit order");
        return getOrders().stream()
                .map(util::convertToResponse)
                .toList();
    }

    public List<CreditOrder> getOrders() {
        return repository.findAll();
    }
    public List<CreditOrder> getOrdersIn_Review() {
        return repository.findAll().stream()
                .filter(creditOrder -> creditOrder.getCreditOrderStatus().equals(CreditOrderStatus.IN_REVIEW))
                .collect(Collectors.toList());
    }
}
