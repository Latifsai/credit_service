package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.auth.JwtTokenProvider;
import com.example.credit_service_project.services.generators.ProductCreatorDTO;
import com.example.credit_service_project.services.product.GetAllProductsService;
import com.example.credit_service_project.services.product.ProductSearchService;
import com.example.credit_service_project.services.user.CustomUserDetailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AccessibleController.class)
@AutoConfigureMockMvc
class AccessibleControllerTest {

    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private CustomUserDetailService detailService;
    @MockBean
    private JwtTokenProvider provider;
    @MockBean
    private GetAllProductsService allProductsService;
    @MockBean
    private ProductSearchService productSearchService;
    @Autowired
    private MockMvc mockMvc;

    @WithAnonymousUser
    @Test
    void getAllProducts() throws Exception {
        List<ProductResponseDTO> productsList = List.of(ProductCreatorDTO.getResponse());

        doReturn(productsList).when(allProductsService).getAllProducts();

        mockMvc.perform(get("/all/products"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1L, 1L)))
//                .andExpect(jsonPath("$[*].name", containsInAnyOrder("BMW X5")))
        ;
    }

    @WithAnonymousUser
    @Test
    void searchProduct() throws Exception {
        mockMvc.perform(get("/all/products/1"))
                .andExpect(status().isOk());
    }
}