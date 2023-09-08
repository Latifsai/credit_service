package com.example.credit_service_project.DTO.creditOrderDTO;

import lombok.Value;

import java.math.BigInteger;
import java.util.UUID;

@Value
public class CreateCreditOrderDTORequest {
    Long productID;
    UUID accountID;
    String accountNumber;
    Integer numberLength;
}
