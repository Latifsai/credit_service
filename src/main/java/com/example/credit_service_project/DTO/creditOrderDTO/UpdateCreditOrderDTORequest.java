package com.example.credit_service_project.DTO.creditOrderDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateCreditOrderDTORequest {
    UUID uuid;

    BigDecimal amount;
    BigDecimal clientIncome;
    BigDecimal clientMonthlyExpenditure;
    CreditOrderStatus creditOrderStatus;
}
