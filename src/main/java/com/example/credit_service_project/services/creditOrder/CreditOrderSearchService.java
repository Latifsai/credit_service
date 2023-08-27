package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
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
public class CreditOrderSearchService {

    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Transactional(readOnly = true)
    public CreditOrderResponseDTO searchCreditOrder(UUID id) {
        CreditOrder creditOrder = findById(id);
        log.info("Search credit order with ID: {}", id);
        return util.convertToResponse(creditOrder);
    }

    public CreditOrder findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ORDER_MESSAGE));
    }
}
