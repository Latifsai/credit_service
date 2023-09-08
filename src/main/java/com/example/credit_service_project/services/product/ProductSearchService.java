package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.ProductRepository;
import com.example.credit_service_project.services.utils.ProductUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSearchService {
    private final ProductRepository repository;
    private final ProductUtil util;

    @Transactional(readOnly = true)
    public ProductResponseDTO searchProduct(Long id) {
        Product product = findById(id);
        log.info("Search product with id: {}", id);
        return util.toResponse(product);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_PRODUCT_MESSAGE));
    }
}
