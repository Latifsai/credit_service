package com.example.credit_service_project.dto.ProductDTO;

import lombok.Value;

import java.util.List;

@Value
public class GetProductsListResponse {
    List<ProductResponseDTO> products;
}
