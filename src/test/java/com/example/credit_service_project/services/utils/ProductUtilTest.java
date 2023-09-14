package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.dto.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.generators.ProductCreatorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductUtilTest {

    @Mock
    private ProductUtil util;

    private Product product;
    private ProductResponseDTO response;

    @BeforeEach
    public void init() {
        product = EntityCreator.getProduct();
        response = ProductCreatorDTO.getResponse();
    }

    @Test
    void convertFromAddRequestToResponse() {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW X5", BigDecimal.valueOf(14000.65),
                "bmw", "USD", CalculationType.DIFFERENTIATED);

        when(util.convertFromAddRequestToResponse(request)).thenReturn(product);
        assertEquals(product, util.convertFromAddRequestToResponse(request));
    }

    @Test
    void toResponse() {
        when(util.toResponse(product)).thenReturn(response);
        assertEquals(response, util.toResponse(product));
    }

    @Test
    void update() {
        Product updatedProduct = EntityCreator.getProductUpdated();

        UpdateProductDTORequest request = new UpdateProductDTORequest(1L, null, null, null, null, null, CalculationType.ANNUITY);
        when(util.update(product, request)).thenReturn(updatedProduct);
        assertEquals(updatedProduct, util.update(product, request));
    }
}