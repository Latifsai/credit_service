package com.example.credit_service_project.DTO.creditDTO;

import lombok.Value;

import java.math.BigInteger;
import java.util.UUID;


@Value
public class AddCreditDTORequest {
    UUID id;
    String creditOrderNumber;

    BigInteger productID;
    String name;

    String rateBase;
    
}
