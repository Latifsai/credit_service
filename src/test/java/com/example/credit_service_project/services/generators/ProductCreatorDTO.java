package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.enums.CalculationType;

import java.math.BigDecimal;

public class ProductCreatorDTO {

    public static ProductResponseDTO getResponse() {
        return ProductResponseDTO.builder()
                .id(1L)
                .name("BMW")
                .sum(BigDecimal.valueOf(10000))
                .needGuaranty(false)
                .earlyRepayment(true)
                .needIncomeDetails(false)
                .details("BMW")
                .currencyCode("USD")
                .calculationType(CalculationType.DIFFERENTIATED)
                .build();
    }
}
