package com.example.credit_service_project.generators;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.enums.CalculationType;

import java.math.BigDecimal;

public class ProductCreatorDTO {

    public static ProductResponseDTO getResponse() {
        return ProductResponseDTO.builder()
                .id(1L)
                .name("BMW X5")
                .sum(BigDecimal.valueOf(14580.65))
                .needGuaranty(false)
                .earlyRepayment(true)
                .needIncomeDetails(false)
                .details("X5")
                .currencyCode("USD")
                .calculationType(CalculationType.DIFFERENTIATED)
                .build();
    }
}
