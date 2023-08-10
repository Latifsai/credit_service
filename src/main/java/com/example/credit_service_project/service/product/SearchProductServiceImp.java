package com.example.credit_service_project.service.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.ProductService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.ProductNotFoundException;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class SearchProductServiceImp implements ProductService<ProductResponseDTO, BigInteger> {
    private final ProductRepository repository;
    private final ProductUtil util;

    @Override
    public ProductResponseDTO execute(BigInteger id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorsMessage.NOT_FOUND_PRODUCT_MESSAGE));
        return util.toResponse(product);
    }
}
