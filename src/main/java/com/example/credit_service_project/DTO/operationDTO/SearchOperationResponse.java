package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class SearchOperationResponse {
    UUID id;
    String accountNumber;
    BigDecimal sum;
    OperationType type;
    LocalDate operationEndMark;
    boolean isDebit;
    String operationDetails;
    String currency;
}
