package com.example.credit_service_project.DTO.creditOrderDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Value
public class AddCreditOrderDTORequest {
    BigInteger productID;
    UUID clientID;
    Integer numberLength;
}
