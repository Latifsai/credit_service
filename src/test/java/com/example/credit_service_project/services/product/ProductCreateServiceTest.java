package com.example.credit_service_project.services.product;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.repositories.ProductRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.utils.ProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCreateServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductUtil util;
    @InjectMocks
    private ProductCreateService productCreateService;

    @Test
    void createProduct() {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW", BigDecimal.valueOf(14500), "BMW",
                "USD", CalculationType.DIFFERENTIATED);
        Product product = EntityCreator.getProduct();

        when(util.convertFromAddRequestToResponse(request)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(util.toResponse(product)).thenReturn(ProductCreatorDTO.getResponse());

        ProductResponseDTO result = productCreateService.createProduct(request);

        assertNotNull(result);

        verify(util, times(1)).convertFromAddRequestToResponse(request);
        verify(repository, times(1)).save(product);
    }

    @Test
    void saveProduct() {
        Product product = EntityCreator.getProduct();
        when(repository.save(product)).thenReturn(product);

        Product saved = productCreateService.saveProduct(product);
        verify(repository, times(1)).save(product);
        assertEquals(product, saved);
    }
}