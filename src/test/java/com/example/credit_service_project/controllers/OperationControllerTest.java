package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.operation.OperationUpdateService;
import com.example.credit_service_project.services.operation.PaymentProcessingService;
import com.example.credit_service_project.services.operation.ReplenishmentAndEarlyPaymentOperationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OperationController.class)
@Import(SecurityConfiguration.class)
class OperationControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private PaymentProcessingService createPaymentOperation;
    @MockBean
    private ReplenishmentAndEarlyPaymentOperationService replenishmentAndEarlyPaymentOperation;
    @MockBean
    private OperationUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "MANAGER")
    @Test
    void handlePayments() throws Exception {
        mockMvc.perform(post("/operation"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void addNewOperation() throws Exception {
        mockMvc.perform(post("/operation/elective"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void update() throws Exception {
        mockMvc.perform(put("/operation"))
                .andExpect(status().isOk());
    }
}