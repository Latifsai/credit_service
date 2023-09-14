package com.example.credit_service_project.controllers;

import com.example.credit_service_project.generators.CreditDTOGenerator;
import com.example.credit_service_project.services.credit.CreditCreateService;
import com.example.credit_service_project.services.credit.GetAllCreditsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreditControllerTest {

    @MockBean
    private CreditCreateService create;
    @MockBean
    private GetAllCreditsService getAllCredits;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void testGetAllCredits() throws Exception {
        var response = CreditDTOGenerator.getResponse();
        when(getAllCredits.getAllCredits()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/credit"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].creditType").value(response.getCreditType()));
    }


    @Test
    void create() throws Exception {
        mockMvc.perform(post("/credit"))
                .andExpect(status().isOk());
    }
}