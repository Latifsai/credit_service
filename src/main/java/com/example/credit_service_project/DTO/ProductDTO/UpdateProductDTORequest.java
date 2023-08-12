package com.example.credit_service_project.DTO.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class UpdateProductDTORequest {
    BigInteger id;

    BigDecimal sum;
    Boolean needGuaranty;
    Boolean earlyRepayment;
    Boolean needIncomeDetails;
    String details;
    CalculationType calculationType;
}
