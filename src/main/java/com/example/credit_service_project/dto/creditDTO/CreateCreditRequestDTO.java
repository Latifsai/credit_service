package com.example.credit_service_project.dto.creditDTO;

import lombok.Value;

import java.util.UUID;


@Value
public class CreateCreditRequestDTO {
    UUID accountID;
    String accountNumber;
    UUID agreementID;
    UUID creditOrderID;
    Integer periodMonth;
    Integer creditHolidaysMonth;
    String creditType;
}
