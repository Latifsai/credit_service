package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdatePaymentScheduleDTOResponse {
    UUID uuid;
    String accountNumber;
    //data to change
    LocalDate paymentDate;
    BigDecimal mainPayment;
    BigDecimal ratePayment;
}
