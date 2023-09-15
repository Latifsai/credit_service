package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.dto.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.generators.ProductCreatorDTO;
import com.example.credit_service_project.service.product.ProductCreateService;
import com.example.credit_service_project.service.product.ProductDeleteService;
import com.example.credit_service_project.service.product.ProductUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductDeleteService delete;
    @MockBean
    private ProductCreateService create;
    @MockBean
    private ProductUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    private final ProductResponseDTO response = ProductCreatorDTO.getResponse();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void add() throws Exception {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW", BigDecimal.valueOf(14500), "BMW",
                "USD", CalculationType.DIFFERENTIATED);

        when(create.createProduct(request)).thenReturn(response);

        mockMvc.perform(post("/product")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.sum").value(response.getSum()))
                .andExpect(jsonPath("$.currencyCode").value(response.getCurrencyCode()));
    }

    @Test
    void addForbidden() throws Exception {
        CreateProductDTORequest request = new CreateProductDTORequest("BMW", BigDecimal.valueOf(14500), "BMW",
                "USD", CalculationType.DIFFERENTIATED);

        when(create.createProduct(request)).thenReturn(response);

        mockMvc.perform(post("/product")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void updateProduct() throws Exception {
        UpdateProductDTORequest request = new UpdateProductDTORequest(1L, null, null,null,
                null,null, CalculationType.ANNUITY);

        when(update.updateProduct(request)).thenReturn(response);
        mockMvc.perform(put("/product")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.sum").value(response.getSum()))
                .andExpect(jsonPath("$.currencyCode").value(response.getCurrencyCode()));
    }

    @Test
    void updateProductForbidden() throws Exception {
        UpdateProductDTORequest request = new UpdateProductDTORequest(1L, null, null,null,
                null,null, CalculationType.ANNUITY);

        when(update.updateProduct(request)).thenReturn(response);
        mockMvc.perform(put("/product")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void testDelete() throws Exception {

        when(delete.deleteProduct(1L)).thenReturn(response);

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.sum").value(response.getSum()))
                .andExpect(jsonPath("$.currencyCode").value(response.getCurrencyCode()));
    }

    @Test
    void testDeleteForbidden() throws Exception {

        when(delete.deleteProduct(1L)).thenReturn(response);

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isForbidden());
    }
}