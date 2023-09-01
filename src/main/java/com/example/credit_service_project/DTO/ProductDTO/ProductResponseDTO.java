package com.example.credit_service_project.DTO.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Value
@Builder
public class ProductResponseDTO {
     Long id;
     String name;
     BigDecimal sum;
     boolean needGuaranty;
     boolean earlyRepayment;
     boolean needIncomeDetails;
     String details;
     String currencyCode;
     CalculationType calculationType;
}
