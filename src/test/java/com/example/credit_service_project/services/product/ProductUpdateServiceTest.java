package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.utils.ProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUpdateServiceTest {

    @Mock
    private ProductCreateService createProductService;
    @Mock
    private ProductSearchService searchProductService;
    @Mock
    private ProductUtil util;
    @InjectMocks
    ProductUpdateService productUpdateService;

    @Test
    void updateProduct() {
        UpdateProductDTORequest request = new UpdateProductDTORequest(1L, null, null,null,
                null,null, CalculationType.ANNUITY);
        Product product = EntityCreator.getProduct();
        Product updatedProduct = EntityCreator.getProductUpdated();

        when(searchProductService.findById(request.getId())).thenReturn(product);
        when(util.update(product,request)).thenReturn(updatedProduct);
        when(createProductService.saveProduct(updatedProduct)).thenReturn(updatedProduct);
        when(util.toResponse(updatedProduct)).thenReturn(ProductCreatorDTO.getResponse());

        ProductResponseDTO result = productUpdateService.updateProduct(request);

        verify(util, times(1)).update(product,request);
        verify(createProductService, times(1)).saveProduct(updatedProduct);
    }
}