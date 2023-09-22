package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DecisionOrderService {

    private final GetAllCreditOrdersService getAllOrdersService;
    private final CreditOrderRepository repository;
    private final CreditOrderUtil util;

    /**
     *In the method all orders that are under consideration are found and checks that if the number of days is 5 or more,
     * then a decision is issued to approve the application
     * @return List<CreditOrderResponseDTO>
     */
    @Scheduled(cron = "0 0 15 * * *")
    public List<CreditOrderResponseDTO> acceptOrder() {
        List<CreditOrder> orders = getAllOrdersService.getOrdersIn_Review();
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
