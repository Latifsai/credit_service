package com.example.credit_service_project.DTO.ProductDTO;

import lombok.Value;

import java.math.BigInteger;

@Value
public class UpdateProductDTORequest {
    BigInteger id;
    String name;

    boolean needGuaranty;
    boolean earlyRepayment;
    boolean needIncomeDetails;
    String details;
}
