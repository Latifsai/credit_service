package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.dto.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductUtilTest {

    @InjectMocks
    private ProductUtil util;

    private Product product;
    private ProductResponseDTO response;

    @BeforeEach
    public void init() {
        product = EntityCreator.getProduct();
        response = ProductCreatorDTO.getResponse();
    }

    @Test
    @DisplayName("Test convertFromAddRequestToResponse method")
    void convertFromAddRequestToResponse() {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW X5", BigDecimal.valueOf(14000.65),
                "bmw", "USD", CalculationType.DIFFERENTIATED);

        assertEquals(product, util.convertFromAddRequestToResponse(request));
    }

    @Test
    @DisplayName("Test toResponse method")
    void toResponse() {
        assertEquals(response, util.toResponse(product));
    }

    @Test
    @DisplayName("Test update method")
    void update() {
        Product updatedProduct = EntityCreator.getProductUpdated();
        UpdateProductDTORequest request = new UpdateProductDTORequest(1L, null, null, null, null, null, CalculationType.ANNUITY);

        assertEquals(updatedProduct, util.update(product, request));
    }
}