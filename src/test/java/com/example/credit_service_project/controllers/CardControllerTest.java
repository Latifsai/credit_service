package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.card.CardCreateService;
import com.example.credit_service_project.services.card.CardUpdateService;
import com.example.credit_service_project.services.card.GetAllCardsService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(SecurityConfiguration.class)
@WebMvcTest(CardController.class)
@AutoConfigureMockMvc
class CardControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private CardCreateService create;
    @MockBean
    private GetAllCardsService get;
    @MockBean
    private CardUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = "MANAGER")
    @Test
    void createCard() throws Exception {
        mockMvc.perform(post("/card"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void getCards() throws Exception {
        mockMvc.perform(get("/card"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void updateCard() throws Exception {
        mockMvc.perform(put("/card"))
            .andExpect(status().isOk());
    }
}