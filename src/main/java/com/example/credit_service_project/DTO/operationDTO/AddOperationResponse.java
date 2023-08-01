package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Value
public class AddOperationResponse {
    String accountNumber;
    BigDecimal balance;
    BigDecimal sum;
    OperationType type;
    LocalDate operationEndMark;
    String operationDetails;
    String currency;
}
