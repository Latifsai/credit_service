package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.CreditOrderService;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.CreditOrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchCreditOrderServiceImp implements CreditOrderService<CreditOrderResponseDTO, UUID> {

    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Override
    public CreditOrderResponseDTO execute(UUID id) {
      CreditOrder creditOrder = findById(id);
        return util.convertToResponse(creditOrder);
    }

    public CreditOrder findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CreditOrderNotFoundException(ErrorsMessage.NOT_FOUND_ORDER_MESSAGE));
    }
}
