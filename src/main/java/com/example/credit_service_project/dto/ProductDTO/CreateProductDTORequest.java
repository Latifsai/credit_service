package com.example.credit_service_project.dto.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateProductDTORequest {
     String name;
     BigDecimal sum;
     String details;
     String currencyCode;
     CalculationType calculationType;
}
