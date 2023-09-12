package com.example.credit_service_project.DTO.creditOrderDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCreditOrderRequestDTO {
    UUID id;
    BigDecimal clientSalary;
    BigDecimal passiveIncome;
    BigDecimal clientMonthlyExpenditure;
    CreditOrderStatus status;

}
