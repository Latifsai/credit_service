package com.example.credit_service_project.controllers;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.services.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.product.GetAllProductsService;
import com.example.credit_service_project.services.product.ProductSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccessibleController.class)
@ExtendWith(SpringExtension.class)
class AccessibleControllerTest {

    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private GetAllProductsService allProductsService;
    @MockBean
    private ProductSearchService productSearchService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Eilley", roles = {"CLIENT"})
    public void getAllProducts() throws Exception {
        ProductResponseDTO response = ProductCreatorDTO.getResponse();
        List<ProductResponseDTO> productsList = Collections.singletonList(response);

        when(allProductsService.getAllProducts()).thenReturn(productsList);

        mockMvc.perform(get("/all/products"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "Eilley", roles = {"CLIENT"})
    void searchProduct() throws Exception {
        ProductResponseDTO response = ProductCreatorDTO.getResponse();

        when(productSearchService.searchProduct(1L)).thenReturn(response);

        mockMvc.perform(get("/all/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product.id").value(1));
    }
}