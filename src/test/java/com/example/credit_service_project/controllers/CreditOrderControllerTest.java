package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.services.creditOrder.CreditOrderUpdateService;
import com.example.credit_service_project.services.creditOrder.DecisionOrderService;
import com.example.credit_service_project.services.creditOrder.GetAllCreditOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(CreditOrderController.class)
class CreditOrderControllerTest {

    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private CreditOrderCreateService create;
    @MockBean
    private DecisionOrderService decisionOrderService;
    @MockBean
    private GetAllCreditOrdersService getAllOrders;
    @MockBean
    private CreditOrderUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "MANAGER")
    @Test
    void create() throws Exception {
        mockMvc.perform(post("/order"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void update() throws Exception {
        mockMvc.perform(put("/order"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void decision() throws Exception {
        mockMvc.perform(put("/order/review"))
                .andExpect(status().isOk());
    }

}