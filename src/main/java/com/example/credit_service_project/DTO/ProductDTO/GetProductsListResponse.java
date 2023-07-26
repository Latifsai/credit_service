package com.example.credit_service_project.DTO.ProductDTO;

import com.example.credit_service_project.entity.Product;
import lombok.Value;

import java.util.List;

@Value
public class GetProductsListResponse {
    List<Product> products;
}
