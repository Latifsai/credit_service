package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class AddOperationRequest {
    String accountNumber;
    BigDecimal mainPayment; //from PaymentSchedule
    BigDecimal ratePayment; //from PaymentSchedule
    OperationType type;
    LocalDate actualPaymentDate;
    StringBuilder operationDetails;
    String accountCurrency;
}
