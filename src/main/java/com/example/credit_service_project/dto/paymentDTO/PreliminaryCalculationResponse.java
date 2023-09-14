package com.example.credit_service_project.dto.paymentDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class PreliminaryCalculationResponse {
    Integer paymentNumber;
    LocalDate paymentDate;
    BigDecimal monthlyPayment;
    String currency;
}
