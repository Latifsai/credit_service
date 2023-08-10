package com.example.credit_service_project.DTO.ProductDTO;

import com.example.credit_service_project.entity.enums.CalculationType;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AddProductDTORequest {
     String name;
     BigDecimal sum;
     String details;
     String currencyCode;
     CalculationType calculationType;
}
