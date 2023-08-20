package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class AddPaymentScheduleDTOResponse {
    String accountNumber;
    LocalDate paymentDate;
    BigDecimal monthlyPayment;
    BigDecimal surcharge;
    BigDecimal sum;
    boolean isPaid;
}
