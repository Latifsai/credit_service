package com.example.credit_service_project.dto.paymentDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class PaymentsBelongsToAccountRequest {
    UUID accountID;
    String accountNumber;
}
