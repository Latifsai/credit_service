package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreateService {

    private final ProductRepository repository;
    private final ProductUtil util;

    /**
     * Create new Product upon request
     * @param request CreateProductDTORequest
     * @return ProductResponseDTO
     */
    public ProductResponseDTO createProduct(CreateProductDTORequest request) {
        Product product = util.convertFromAddRequestToResponse(request);
        Product savedProduct = saveProduct(product);
        log.info("Create product: {}", savedProduct);
        return util.toResponse(savedProduct);
    }

    /**
     * Save Product inn DB
     * @param product Product
     * @return Product
     */
     public Product saveProduct(Product product) {
        return repository.save(product);
     }
}
