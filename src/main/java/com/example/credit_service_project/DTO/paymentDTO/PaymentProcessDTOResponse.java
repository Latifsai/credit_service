package com.example.credit_service_project.DTO.paymentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessDTOResponse {
    private String cardNumber;
    private String holderName;
    private BigDecimal balance;
    private LocalDate actualPaymentDate;
    private boolean isPaid;
}
