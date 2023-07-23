package com.example.credit_service_project.DTO.paymentDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PaymentDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    String paymentDate;
    BigDecimal mainPayment;
    BigDecimal ratePayment;
}
