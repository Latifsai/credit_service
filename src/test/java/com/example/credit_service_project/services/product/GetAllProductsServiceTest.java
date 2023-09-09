package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.repositories.ProductRepository;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.utils.ProductUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllProductsServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductUtil util;
    @InjectMocks
    private GetAllProductsService getAllProductsService;

    @Test
    void getAllProducts() {
        Product product = EntityCreator.getProduct();
        ProductResponseDTO responseDTO = ProductCreatorDTO.getResponse();

        when(repository.findAll()).thenReturn(List.of(product));
        when(util.toResponse(product)).thenReturn(responseDTO);

        List<ProductResponseDTO> result = getAllProductsService.getAllProducts();

        verify(repository,times(1)).findAll();
        verify(util,times(1)).toResponse(product);
        assertNotNull(result);
    }
}