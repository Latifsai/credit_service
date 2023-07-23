package com.example.credit_service_project.DTO.ProductDTO;

import lombok.Value;

import java.math.BigInteger;

@Value
public class DeleteAndSearchProductDTORequest {
    BigInteger id;
    String name;
}
