package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class SearchAndDeletePaymentScheduleDTOResponse {
    LocalDate paymentDate;
    BigDecimal mainPayment;
    BigDecimal ratePayment;
}
