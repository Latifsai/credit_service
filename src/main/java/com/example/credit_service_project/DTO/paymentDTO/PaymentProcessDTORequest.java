package com.example.credit_service_project.DTO.paymentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessDTORequest {
    private String cardNumber;
    private BigDecimal cardBalance;
    private BigDecimal surcharge;
    private BigDecimal mainPayment;
    private BigDecimal ratePayment;
}
