package com.example.credit_service_project.DTO.operationDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class AddOperationPaymentRequest {
    UUID accountID;
    String accountNumber;
    String operationDetails;
}
