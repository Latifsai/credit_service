package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Value
public class UpdateOperationsResponse {
    UUID id;
    Timestamp operationEndMark;
    boolean isDebit;
    BigDecimal sum;
    OperationType type;
    String currency;
    StringBuilder operationDetails;

}
