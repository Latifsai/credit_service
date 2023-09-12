package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.credit.CreditCreateService;
import com.example.credit_service_project.services.credit.GetAllCreditsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(CreditController.class)
@AutoConfigureMockMvc
class CreditControllerTest {

    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private CreditCreateService create;
    @MockBean
    private GetAllCreditsService getAllCredits;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "MANAGER")
    @Test
    void tesGet() throws Exception {
        mockMvc.perform(get("/credit"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/credit"))
                .andExpect(status().isOk());
    }
}