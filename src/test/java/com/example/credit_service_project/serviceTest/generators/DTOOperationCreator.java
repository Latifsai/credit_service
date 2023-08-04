package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.DTO.operationDTO.*;
import com.example.credit_service_project.entity.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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

    public static OperationResponseDTO getOperationResponseDTO() {
        return new OperationResponseDTO(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                new BigDecimal("100"),
                OperationType.SPENDING,
                LocalDate.of(2023,8,1),
                "Bought a products in shop.",
                "$"
        );
    }

    public static SearchOperationResponse getSearchResponse() {
        return new SearchOperationResponse(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                "A10B3U3OI9",
                new BigDecimal("100"),
                OperationType.SPENDING,
                LocalDate.of(2023,8,1),
                true,
                "Bought a products in shop.",
                "$"
        );
    }
    public static UpdateOperationsResponse getUpdateResponse() {
        return new UpdateOperationsResponse(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                LocalDate.of(2023,8,1),
                true,
                new BigDecimal("100"),
                OperationType.MONTHLY_PAYMENT,
                "$",
                "Bought a products in shop."
        );
    }


}
