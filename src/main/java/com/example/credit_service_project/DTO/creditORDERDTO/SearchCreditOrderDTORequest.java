package com.example.credit_service_project.DTO.creditORDERDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class SearchCreditOrderDTORequest {
    UUID id;
    LocalDate creationDate;
    String number;
    Integer periodMonths;
    BigDecimal amount;
}
