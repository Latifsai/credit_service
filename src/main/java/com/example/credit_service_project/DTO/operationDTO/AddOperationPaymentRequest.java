package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOperationPaymentRequest {
    private String accountNumber;
    private BigDecimal mainPayment; //from PaymentSchedule
    private BigDecimal ratePayment; //from PaymentSchedule
    private OperationType type;
    private LocalDate actualPaymentDate;
    private String operationDetails;
    private String accountCurrency;
}
