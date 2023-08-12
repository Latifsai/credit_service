package com.example.credit_service_project.service.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsListService {

    private final ProductRepository repository;
    private final ProductUtil util;

    public List<ProductResponseDTO> execute() {
        return repository.findAll().stream()
                .map(util::toResponse)
                .toList();
    }
}
