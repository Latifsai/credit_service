package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateOperationsResponse {
    UUID id;
    LocalDate operationEndMark;
    boolean isDebit;
    BigDecimal sum;
    OperationType type;
    String currency;
    String operationDetails;

}
