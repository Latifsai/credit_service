package com.example.credit_service_project.controller;

import com.example.credit_service_project.generators.CreditHistoryDTOGenerator;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreditHistoryControllerTest {

    @MockBean
    private CreditHistoryService creditHistoryService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test findAll method")
    void findAll() throws Exception {
        var list = Collections.singletonList(CreditHistoryDTOGenerator.getResponse());
        when(creditHistoryService.findAllHistories()).thenReturn(list);

        mockMvc.perform(get("/credit_history"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test findAll method if empty")
    void findAllIfEmpty() throws Exception {
        when(creditHistoryService.findAllHistories()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/credit_history"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test findAllForbidden method")
    void findAllForbidden() throws Exception {
        var list = Collections.singletonList(CreditHistoryDTOGenerator.getResponse());
        when(creditHistoryService.findAllHistories()).thenReturn(list);

        mockMvc.perform(get("/credit_history"))
                .andExpect(status().isForbidden());
    }

}