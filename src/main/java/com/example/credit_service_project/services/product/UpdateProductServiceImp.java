package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.ProductService;
import com.example.credit_service_project.services.utils.ProductUtil;
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
        Product product = searchProductService.findById(request.getId());

        Product updatedProduct = util.update(product, request);
        Product savedProduct = createProductService.saveProduct(updatedProduct);

        return util.toResponse(savedProduct);
    }
}
