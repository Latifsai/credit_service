package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.services.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsListService {

    private final ProductRepository repository;
    private final ProductUtil util;

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> execute() {
        return repository.findAll().stream()
                .map(util::toResponse)
                .toList();
    }
}
