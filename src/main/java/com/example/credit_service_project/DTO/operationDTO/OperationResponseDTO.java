package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class OperationResponseDTO {
    UUID id;
    BigDecimal sum;
    OperationType type;
    LocalDate operationEndMark;
    String operationDetails;
    String currency;
}
