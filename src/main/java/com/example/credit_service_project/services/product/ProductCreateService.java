package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.ProductRepository;
import com.example.credit_service_project.services.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreateService {

    private final ProductRepository repository;
    private final ProductUtil util;

    public ProductResponseDTO createProduct(AddProductDTORequest request) {
        Product product = util.convertFromAddRequestToResponse(request);
        Product savedProduct = saveProduct(product);
        log.info("Create product: {}", savedProduct);
        return util.toResponse(savedProduct);
    }
     public Product saveProduct(Product product) {
        return repository.save(product);
     }
}
