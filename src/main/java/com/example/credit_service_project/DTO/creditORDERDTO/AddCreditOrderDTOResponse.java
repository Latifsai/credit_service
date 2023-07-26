package com.example.credit_service_project.DTO.creditORDERDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class AddCreditOrderDTOResponse {
    UUID productID;
    String productName;
    String number;
    BigDecimal amount;
    LocalDate creationDate;
    Integer periodMonths;
    CreditOrderStatus creditOrderStatus;
}
