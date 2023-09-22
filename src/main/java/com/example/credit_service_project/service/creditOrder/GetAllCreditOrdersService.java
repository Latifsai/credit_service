package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
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

    /**
     * Method get all Orders in database and convert to response
     * @return List
     */
    @Transactional(readOnly = true)
    public List<CreditOrderResponseDTO> getAllCreditOrders() {
        log.info("Get a list of credit order");
        return getOrders().stream()
                .map(util::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all Orders in DB
     * @return List<CreditOrder>
     */
    public List<CreditOrder> getOrders() {
        return repository.findAll();
    }

    /**
     * Here will be found all Orders with status IN_REVIEW
     * @return List
     */
    public List<CreditOrder> getOrdersIn_Review() {
        return repository.findAll().stream()
                .filter(creditOrder -> creditOrder.getCreditOrderStatus().equals(CreditOrderStatus.IN_REVIEW))
                .collect(Collectors.toList());
    }
}
