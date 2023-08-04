package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOperationRequestSpendingOrReplenishment {
    private UUID accountID;
    private UUID cardID;
    private String accountNumber;
    private String cardNumber;
    private BigDecimal sum;
    private OperationType type;
    private LocalDate actualPaymentDate;
    private String operationDetails;
}
