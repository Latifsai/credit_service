package com.example.credit_service_project.DTO.creditDTO;

import lombok.Value;

import java.util.UUID;


@Value
public class CreateCreditDTORequest {
    UUID accountID;
    String accountNumber;
    UUID agreementID;
    UUID creditOrderID;
    Integer periodMonth;
    Integer creditHolidaysMonth;
    String creditType;
}
