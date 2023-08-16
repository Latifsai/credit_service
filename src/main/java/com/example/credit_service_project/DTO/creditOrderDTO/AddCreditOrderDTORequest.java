package com.example.credit_service_project.DTO.creditOrderDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class AddCreditOrderDTORequest {
    BigInteger productID;
    BigDecimal clientIncome;
    BigDecimal clientMonthlyExpenditure;
    Integer numberLength;
}
