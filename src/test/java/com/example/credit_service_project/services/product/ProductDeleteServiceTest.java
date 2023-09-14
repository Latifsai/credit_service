package com.example.credit_service_project.services.product;

import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.utils.ProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDeleteServiceTest {

    @Mock
    private ProductSearchService searchProductService;
    @Mock
    private ProductUtil util;
    @InjectMocks
    ProductDeleteService deleteService;

    @Test
    void deleteProduct() {
        Product product = EntityCreator.getProduct();
        when(searchProductService.findById(1L)).thenReturn(product);
        when(util.toResponse(product)).thenReturn(ProductCreatorDTO.getResponse());

        var result = deleteService.deleteProduct(1L);

        verify(searchProductService, times(1)).findById(1L);
        verify(util, times(1)).toResponse(product);
        assertNotNull(result);

    }
}