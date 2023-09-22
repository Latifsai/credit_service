package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDeleteService {
    private final ProductSearchService searchProductService;
    private final ProductUtil util;

    /**
     * Delete Product by ID
     * @param id Long
     * @return ProductResponseDTO
     */
    public ProductResponseDTO deleteProduct(Long id) {
        Product product = searchProductService.findById(id);
        log.info("Delete product with ID: {}", id);
        return util.toResponse(product);
    }
}
