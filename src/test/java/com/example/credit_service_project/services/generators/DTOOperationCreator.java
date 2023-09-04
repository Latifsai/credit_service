package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DTOOperationCreator {

    public static AddOperationRequestSpendingOrReplenishment getRequestSpendingOrReplenishment() {
        return new AddOperationRequestSpendingOrReplenishment(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "A10B3U3OI9",
                new BigDecimal("100"),
                OperationType.SPENDING,
                "Bought a products in shop."
                );
    }

    public static AddOperationRequestSpendingOrReplenishment getRequestSpendingOrReplenishmentWithValidationErrors() {
        return new AddOperationRequestSpendingOrReplenishment(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "",
                "A10B3U3OI9",
                BigDecimal.valueOf(0),
                null,
                null
        );
    }
    public static OperationResponseDTO getOperationResponseDTO() {
        return new OperationResponseDTO(
                "A10B3U3OI9",
                new BigDecimal("3000"),
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                new BigDecimal("100"),
                true,
                OperationType.SPENDING,
                LocalDateTime.of(2023,8,1,17,23,43),
                "Bought a products in shop.",
                "$"
        );
    }

    public static OperationResponseDTO getUpdateOperationResponseDTO() {
        return new OperationResponseDTO(
                "A10B3U3OI9",
                new BigDecimal("3000"),
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                new BigDecimal("100"),
                true,
                OperationType.MONTHLY_PAYMENT,
                LocalDateTime.of(2023,8,1,17,23,43),
                "Mortgage payment",
                "$"
        );
    }
}
