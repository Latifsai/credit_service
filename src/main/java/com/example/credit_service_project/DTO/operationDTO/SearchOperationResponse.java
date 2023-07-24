package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Value
public class SearchOperationResponse {
    UUID id;
    String accountNumber;
    BigDecimal sum;
    OperationType type;
    Timestamp operationEndMark;
    boolean isDebit;
    StringBuilder operationDetails;
    String currency;
}
