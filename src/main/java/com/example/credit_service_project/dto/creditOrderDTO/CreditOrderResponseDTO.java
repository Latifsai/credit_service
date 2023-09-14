package com.example.credit_service_project.dto.creditOrderDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreditOrderResponseDTO {
    UUID id;
    String number;
    BigDecimal amount;
    LocalDate creationDate;
    Integer maxPeriodMonths;
    Integer minPeriodMonths;
    CreditOrderStatus creditOrderStatus;
}
