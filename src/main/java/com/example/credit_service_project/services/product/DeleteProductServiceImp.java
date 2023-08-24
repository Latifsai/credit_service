package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.ProductService;
import com.example.credit_service_project.services.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class DeleteProductServiceImp implements ProductService<ProductResponseDTO, BigInteger> {
    private final SearchProductServiceImp searchProductService;
    private final ProductUtil util;

    @Override
    public ProductResponseDTO execute(BigInteger id) {
        Product product = searchProductService.findById(id);
        return util.toResponse(product);
    }
}
