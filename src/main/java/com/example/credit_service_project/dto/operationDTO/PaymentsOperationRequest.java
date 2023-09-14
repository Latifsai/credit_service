package com.example.credit_service_project.dto.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class PaymentsOperationRequest {
    UUID accountID;
    String accountNumber;
    BigDecimal sum;
    OperationType type;
    String operationDetails;
}
