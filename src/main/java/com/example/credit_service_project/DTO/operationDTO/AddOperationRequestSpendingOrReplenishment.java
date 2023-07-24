package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AddOperationRequestSpendingOrReplenishment {
    String accountNumber;
    BigDecimal sum;
    OperationType type;
    StringBuilder operationDetails;
    String currencyAccount;
}
