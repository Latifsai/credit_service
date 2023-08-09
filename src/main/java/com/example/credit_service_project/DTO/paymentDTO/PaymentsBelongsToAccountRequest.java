package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class PaymentsBelongsToAccountRequest {
    UUID accountID;
    String accountNumber;
}
