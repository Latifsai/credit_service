package com.example.credit_service_project.DTO.ProductDTO;

import lombok.Value;

import java.util.List;

@Value
public class GetProductsListResponse {
    List<ProductResponseDTO> products;
}
