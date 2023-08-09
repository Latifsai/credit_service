package com.example.credit_service_project.DTO.paymentDTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class AddPaymentRequestDTO {
    UUID accountID;
    String accountNumber;

    @FutureOrPresent(message = "paymentDate must mot be past!")
    LocalDate paymentDate;

    @NotNull(message = "mainPayment must not be null!")
    BigDecimal mainPayment;

    @NotNull(message = "ratePayment must not be null!")
    BigDecimal ratePayment;
}
