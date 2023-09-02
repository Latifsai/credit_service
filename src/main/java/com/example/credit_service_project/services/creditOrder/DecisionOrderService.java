package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 Here is the process of accepting loan applications. Applications are processed within 5 days.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionOrderService {

    private final GetAllCreditOrdersService getAllCreditService;
    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Scheduled(cron = "0 0 15 * * *")
    public List<CreditOrderResponseDTO> acceptOrder() {
        List<CreditOrder> orders = getAllCreditService.getOrders();
        List<CreditOrderResponseDTO> responsesList = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (CreditOrder order : orders) {
            LocalDate latsUpdate = order.getLastUpdateDate();
            int daysSinceLastUpdate = latsUpdate.until(today).getDays();
            if (daysSinceLastUpdate >= 5) {
                CreditOrder creditOrder = util.considerationOfApplication(order);
                repository.save(creditOrder);
                responsesList.add(util.convertToResponse(creditOrder));
            }
        }
        log.info("Consider the order");
        return responsesList;
    }

}
