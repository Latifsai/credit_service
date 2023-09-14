package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditOrderUpdateService {

    private final CreditOrderCreateService addCreditOrderService;
    private final CreditOrderSearchService searchCreditOrderService;
    private final CreditOrderUtil util;

    public CreditOrderResponseDTO updateCreditOrder(UpdateCreditOrderRequestDTO request) {
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getId());
        CreditOrder updated = util.update(request, creditOrder);
        addCreditOrderService.saveOrder(updated);
        log.info("Update credit order with number: {}", creditOrder.getNumber());
        return util.convertToResponse(updated);
    }
}
