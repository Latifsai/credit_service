package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.agreement.GetAllAgreementsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(AgreementController.class)
@AutoConfigureMockMvc
class AgreementControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private AgreementCreateService create;
    @MockBean
    private GetAllAgreementsService get;
    @Autowired
    private MockMvc mockMvc;
    @WithMockUser(roles = "MANAGER")
    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/agreement"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/agreement"))
                .andExpect(status().isOk());
    }
}