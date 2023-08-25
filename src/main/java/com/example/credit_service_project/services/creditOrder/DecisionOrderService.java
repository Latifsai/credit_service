package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionOrderService {

    private final GetAllCreditOrdersService getAllCreditService;
    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    @Scheduled(cron = "0 0 15 * * *")
    public List<CreditOrderResponseDTO> execute() {
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
        return responsesList;
    }

}
