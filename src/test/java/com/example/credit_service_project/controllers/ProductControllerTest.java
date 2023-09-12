package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.product.ProductCreateService;
import com.example.credit_service_project.services.product.ProductDeleteService;
import com.example.credit_service_project.services.product.ProductUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@Import(SecurityConfiguration.class)
class ProductControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private ProductDeleteService delete;
    @MockBean
    private ProductCreateService create;
    @MockBean
    private ProductUpdateService update;
    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(roles = "MANAGER")
    @Test
    void add() throws Exception {
        mockMvc.perform(post("/product"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void updateProduct() throws Exception {
        mockMvc.perform(put("/product"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());
    }
}