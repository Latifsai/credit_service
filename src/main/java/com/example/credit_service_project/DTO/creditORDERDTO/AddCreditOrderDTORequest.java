package com.example.credit_service_project.DTO.creditORDERDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AddCreditOrderDTORequest {
    UUID productID;
    String productName;
    BigDecimal clientIncome;
    BigDecimal clientMonthlyExpenditure;
    Integer periodMonths;
}
