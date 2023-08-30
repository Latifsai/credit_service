package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUpdateService {

    private final ProductCreateService createProductService;
    private final ProductSearchService searchProductService;
    private final ProductUtil util;

    public ProductResponseDTO updateProduct(UpdateProductDTORequest request) {
        Product product = searchProductService.findById(request.getId());
        Product updatedProduct = util.update(product, request);
        Product savedProduct = createProductService.saveProduct(updatedProduct);
        log.info("Update product with ID: {}", updatedProduct);
        return util.toResponse(savedProduct);
    }
}
