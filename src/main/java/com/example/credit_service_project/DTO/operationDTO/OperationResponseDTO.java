package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class OperationResponseDTO {
    String accountNumber;
    BigDecimal balance;
    UUID id;
    BigDecimal sum;
    boolean isDebit;
    OperationType type;
    LocalDateTime operationEndMark;
    String operationDetails;
    String currency;
}
