package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class UpdatePaymentScheduleDTORequest {
    UUID uuid;
    //data to change
    LocalDate actualPaymentDate;
    BigDecimal mainPayment;
    BigDecimal ratePayment;
}
