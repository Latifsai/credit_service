package com.example.credit_service_project.DTO.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class UpdateProductDTORequest {
    BigInteger id;

    BigDecimal sum;
    boolean needGuaranty;
    boolean earlyRepayment;
    boolean needIncomeDetails;
    String details;
    CalculationType calculationType;
}
