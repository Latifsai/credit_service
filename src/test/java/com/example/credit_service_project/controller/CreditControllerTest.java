package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.generators.CreditDTOGenerator;
import com.example.credit_service_project.service.credit.CreditCreateService;
import com.example.credit_service_project.service.credit.GetAllCreditsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreditControllerTest {

    @MockBean
    private CreditCreateService create;
    @MockBean
    private GetAllCreditsService getAllCredits;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Test getAllCredits method ")
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void getAllCredits() throws Exception {
        var response = CreditDTOGenerator.getResponse();
        when(getAllCredits.getAllCredits()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/credit"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].creditType").value(response.getCreditType()));
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"Client"})
    @DisplayName("Test getAllCreditsForbidden method ")
    void getAllCreditsForbidden() throws Exception {
        var response = CreditDTOGenerator.getResponse();
        when(getAllCredits.getAllCredits()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/credit"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test create method ")
    void create() throws Exception {

        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"),
                UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"), 12, 0, "Consumer credit");


        var response = CreditDTOGenerator.getCreationResponse();

        when(create.createCredit(request)).thenReturn(response);

        mockMvc.perform(post("/credit")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.creditType").exists())
                .andExpect(jsonPath("$.periodMonth").exists())
                .andExpect(jsonPath("$.currency").exists());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    @DisplayName("Test createForbidden method ")
    void createForbidden() throws Exception {

        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"),
                UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"), 12, 0, "Consumer credit");


        var response = CreditDTOGenerator.getCreationResponse();

        when(create.createCredit(request)).thenReturn(response);

        mockMvc.perform(post("/credit")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}