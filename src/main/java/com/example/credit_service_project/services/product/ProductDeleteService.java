package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDeleteService {
    private final ProductSearchService searchProductService;
    private final ProductUtil util;

    public ProductResponseDTO deleteProduct(BigInteger id) {
        Product product = searchProductService.findById(id);
        log.info("Delete product with ID: {}", id);
        return util.toResponse(product);
    }
}
