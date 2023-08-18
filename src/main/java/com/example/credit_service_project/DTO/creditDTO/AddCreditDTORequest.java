package com.example.credit_service_project.DTO.creditDTO;

import lombok.Value;

import java.util.UUID;


@Value
public class AddCreditDTORequest {
    UUID accountID;
    String accountNumber;
    UUID agreementID;
    UUID creditOrderID;
    Integer periodMonth;
    String creditType;
}
