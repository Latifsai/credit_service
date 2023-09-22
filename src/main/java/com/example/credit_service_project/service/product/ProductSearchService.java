package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.utils.ProductUtil;
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

    /**
     * Search Product by ID and convert to response
     * @param id Long
     * @return ProductResponseDTO
     */
    @Transactional(readOnly = true)
    public ProductResponseDTO searchProduct(Long id) {
        Product product = findById(id);
        log.info("Search product with id: {}", id);
        return util.toResponse(product);
    }

    /**
     * Find Product by ID
     * @param id Long
     * @return Product
     */
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_PRODUCT_MESSAGE));
    }
}
