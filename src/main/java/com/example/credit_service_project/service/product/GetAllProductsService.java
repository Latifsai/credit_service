package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllProductsService {

    private final ProductRepository repository;
    private final ProductUtil util;

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        log.info("Get a list of Products");
        return repository.findAll().stream()
                .map(util::toResponse)
                .collect(Collectors.toList());
    }
}
