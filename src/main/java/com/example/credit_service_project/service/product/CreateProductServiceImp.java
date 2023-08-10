package com.example.credit_service_project.service.product;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.ProductService;
import com.example.credit_service_project.service.utils.ProductUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateProductServiceImp implements ProductService<ProductResponseDTO, AddProductDTORequest> {

    private final ProductRepository repository;
    private final ProductUtil util;

    @Override
    public ProductResponseDTO execute(AddProductDTORequest request) {
        Product product = util.convertFromAddRequestToResponse(request);
        repository.save(product);
        return util.toResponse(product);
    }
}
