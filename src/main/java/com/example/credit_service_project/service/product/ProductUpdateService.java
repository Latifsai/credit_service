package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.dto.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.utils.ProductUtil;
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

    /**
     * Update Product
     * @param request UpdateProductDTORequest
     * @return ProductResponseDTO
     */
    public ProductResponseDTO updateProduct(UpdateProductDTORequest request) {
        Product product = searchProductService.findById(request.getId());
        Product updatedProduct = util.update(product, request);
        Product savedProduct = createProductService.saveProduct(updatedProduct);
        log.info("Update product with ID: {}", updatedProduct);
        return util.toResponse(savedProduct);
    }
}
