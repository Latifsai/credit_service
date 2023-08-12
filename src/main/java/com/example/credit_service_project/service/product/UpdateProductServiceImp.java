package com.example.credit_service_project.service.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.ProductService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.ProductNotFoundException;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductServiceImp implements ProductService<ProductResponseDTO, UpdateProductDTORequest> {

    private final CreateProductServiceImp createProductService;
    private final SearchProductServiceImp searchProductService;
    private final ProductUtil util;


    @Override
    public ProductResponseDTO execute(UpdateProductDTORequest request) {
        Product product = searchProductService.findById(request.getId())
                .orElseThrow(() -> new ProductNotFoundException(ErrorsMessage.NOT_FOUND_PRODUCT_MESSAGE));

        Product updatedProduct = util.update(product, request);
        Product savedProduct = createProductService.saveProduct(updatedProduct);

        return util.toResponse(savedProduct);
    }
}
