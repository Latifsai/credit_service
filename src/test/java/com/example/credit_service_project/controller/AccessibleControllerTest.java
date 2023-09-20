package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.service.product.GetAllProductsService;
import com.example.credit_service_project.service.product.ProductSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AccessibleControllerTest {

    @MockBean
    private GetAllProductsService allProductsService;
    @MockBean
    private ProductSearchService productSearchService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "Test get all products method")
    public void getAllProducts() throws Exception {
        ProductResponseDTO response = ProductCreatorDTO.getResponse();
        List<ProductResponseDTO> productsList = Collections.singletonList(response);

        when(allProductsService.getAllProducts()).thenReturn(productsList);

        mockMvc.perform(get("/all/products"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName(value = "test search product method")
    void searchProduct() throws Exception {
        ProductResponseDTO response = ProductCreatorDTO.getResponse();

        when(productSearchService.searchProduct(1L)).thenReturn(response);

        mockMvc.perform(get("/all/products/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}