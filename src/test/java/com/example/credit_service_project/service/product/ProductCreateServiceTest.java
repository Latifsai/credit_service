package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.service.utils.ProductUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
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
    private final Product product = EntityCreator.getProduct();

    @Test
    @DisplayName("Test createProduct method")
    void createProduct() {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW", BigDecimal.valueOf(14500), "BMW",
                "USD", CalculationType.DIFFERENTIATED);

        when(util.convertFromAddRequestToResponse(request)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(util.toResponse(product)).thenReturn(ProductCreatorDTO.getResponse());

        ProductResponseDTO result = productCreateService.createProduct(request);

        assertNotNull(result);

        verify(util, times(1)).convertFromAddRequestToResponse(request);
        verify(repository, times(1)).save(product);
    }

    @Test
    @DisplayName("Test saveProduct method")
    void saveProduct() {
        when(repository.save(product)).thenReturn(product);

        Product saved = productCreateService.saveProduct(product);
        verify(repository, times(1)).save(product);
        assertEquals(product, saved);
    }

    @Test
    @DisplayName("Test saveProduct method validation")
    void saveProductValidation() {
        product.setName(" ");
        product.setDetails(" ");
        product.setCurrencyCode(null);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(product);

        assertEquals(3, set.size());
    }

}