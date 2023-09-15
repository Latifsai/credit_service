package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.service.utils.ProductUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductSearchServiceTest {
    @Mock
    private ProductRepository repository;
    @Mock
    private ProductUtil util;
    @InjectMocks
    private ProductSearchService productSearchService;

    @Test
    void searchProduct() {
        Long id = 1L;
        Product product = EntityCreator.getProduct();

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(util.toResponse(product)).thenReturn(ProductCreatorDTO.getResponse());

        ProductResponseDTO result = productSearchService.searchProduct(id);

        verify(repository, times(1)).findById(id);
        verify(util, times(1)).toResponse(product);

        assertNotNull(result);
    }

    @Test
    void findById() {
        Long id = 1L;
        Product product = EntityCreator.getProduct();

        when(repository.findById(id)).thenReturn(Optional.of(product));
        Product result = productSearchService.findById(id);

        verify(repository, times(1)).findById(id);
        assertNotNull(result);
    }

    @Test
    void findByIdNotFoundException() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productSearchService.findById(id));

    }
}