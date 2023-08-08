package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class PaymentResponseDTO {
    UUID id;
    LocalDate paymentDate;
    LocalDate actualPaymentDate;
    BigDecimal surcharge;
    BigDecimal mainPayment;
    BigDecimal ratePayment;
    boolean isPaid;
}
