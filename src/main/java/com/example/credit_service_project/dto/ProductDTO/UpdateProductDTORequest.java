package com.example.credit_service_project.dto.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UpdateProductDTORequest {
    Long id;
    BigDecimal sum;
    Boolean needGuaranty;
    Boolean earlyRepayment;
    Boolean needIncomeDetails;
    String details;
    CalculationType calculationType;
}
