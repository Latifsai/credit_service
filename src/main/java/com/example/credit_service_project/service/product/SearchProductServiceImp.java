package com.example.credit_service_project.service.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.ProductService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.ProductNotFoundException;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchProductServiceImp implements ProductService<ProductResponseDTO, BigInteger> {
    private final ProductRepository repository;
    private final ProductUtil util;

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDTO execute(BigInteger id) {
        Product product = findById(id);
        return util.toResponse(product);
    }

    public Product findById(BigInteger id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ErrorsMessage.NOT_FOUND_PRODUCT_MESSAGE));
    }
}
