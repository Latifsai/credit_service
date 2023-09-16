package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CheckCreditOrderStatusResponse;
import com.example.credit_service_project.entity.CreditOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckCreditOrderStatusService {
    private final CreditOrderSearchService searchService;

    public CheckCreditOrderStatusResponse checkOrderStatus(UUID id) {
        CreditOrder order = searchService.findById(id);
        Integer amountDaysInProcessing = (int) ChronoUnit.DAYS.between(order.getCreationDate(), LocalDate.now());
        log.info("Check credit order status with ID: {}", id);
        return new CheckCreditOrderStatusResponse(order.getId(), order.getCreditOrderStatus(), order.getCreationDate(), amountDaysInProcessing);
    }

}
