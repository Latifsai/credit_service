package com.example.credit_service_project.DTO.creditOrderDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCreditOrderDTORequest {
    UUID uuid;

    BigDecimal amount;
    BigDecimal clientSalary;
    BigDecimal passiveIncome;
    BigDecimal clientMonthlyExpenditure;
}
