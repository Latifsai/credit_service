package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.DTO.operationDTO.AddOperationRequestSpendingOrReplenishment;
import com.example.credit_service_project.DTO.operationDTO.AddOperationResponse;
import com.example.credit_service_project.entity.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTOOperationCreator {

    public static AddOperationRequestSpendingOrReplenishment getRequestSpendingOrReplenishment() {
        return new AddOperationRequestSpendingOrReplenishment(
                "A10B3U3OI9",
                new BigDecimal("100"),
                OperationType.SPENDING,
                LocalDate.of(2023,8,1),
                "Bought a products in shop."
                );
    }

    public static AddOperationResponse getAddResponse() {
        return new AddOperationResponse(
                "A10B3U3OI9",
                new BigDecimal("2900"),
                new BigDecimal("100"),
                OperationType.SPENDING,
                LocalDate.of(2023,8,1),
                "Bought a products in shop.",
                "$"
        );
    }
}
