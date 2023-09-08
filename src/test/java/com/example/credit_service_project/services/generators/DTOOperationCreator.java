package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DTOOperationCreator {

    public static OperationResponseDTO getOperationResponseDTO() {
        return  OperationResponseDTO.builder()
                .accountNumber("A10B3U3OI9")
                .balance(new BigDecimal("3600"))
                .id(UUID.fromString("11117777-9999-1111-b491-426655440000"))
                .sum(new BigDecimal("300"))
                .isDebit(true)
                .type(OperationType.MONTHLY_PAYMENT)
                .operationEndMark(LocalDateTime.of(2023,8,1,17,23,43))
                .operationDetails("Bought a products in shop.")
                .currency("USD")
                .build();
    }

    public static OperationResponseDTO getOperationResponseREPLENISHMENT() {
        return  OperationResponseDTO.builder()
                .accountNumber("A10B3U3OI9")
                .balance(new BigDecimal("3600"))
                .id(UUID.fromString("11117777-9999-1111-b491-426655440000"))
                .sum(new BigDecimal("1000"))
                .isDebit(true)
                .type(OperationType.REPLENISHMENT)
                .operationEndMark(LocalDateTime.of(2023,8,1,17,23,43))
                .operationDetails("Bought a products in shop.")
                .currency("USD")
                .build();
    }
    public static OperationResponseDTO getUpdateOperationResponseDTO() {
            return  OperationResponseDTO.builder()
                .accountNumber("A10B3U3OI9")
                .balance(new BigDecimal("3600"))
                .id(UUID.fromString("11117777-9999-1111-b491-426655440000"))
                .sum(new BigDecimal("300"))
                .isDebit(true)
                .type(OperationType.EARLY_REPAYMENT)
                .operationEndMark(LocalDateTime.of(2023,8,1,17,23,43))
                .operationDetails("EARLY_REPAYMENT")
                .currency("USD")
                .build();
    }
}
