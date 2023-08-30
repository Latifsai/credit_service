package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
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
