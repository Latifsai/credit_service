package com.example.credit_service_project.DTO.ProductDTO;

import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class DeleteAndSearchProductDTOResponse {
    BigInteger id;
    String name;
    BigDecimal sum;
    String currencyCode;

}
