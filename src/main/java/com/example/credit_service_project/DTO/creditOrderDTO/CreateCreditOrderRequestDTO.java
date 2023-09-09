package com.example.credit_service_project.DTO.creditOrderDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateCreditOrderRequestDTO {
    Long productID;
    UUID accountID;
    String accountNumber;
    Integer numberLength;
}
