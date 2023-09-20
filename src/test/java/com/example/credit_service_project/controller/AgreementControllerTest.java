package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.generators.AgreementDTOGenerator;
import com.example.credit_service_project.service.agreement.AgreementCreateService;
import com.example.credit_service_project.service.agreement.GetAllAgreementsService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AgreementControllerTest {

    @MockBean
    private AgreementCreateService create;
    @MockBean
    private GetAllAgreementsService get;
    @Autowired
    private MockMvc mockMvc;

    private final AgreementResponse response = AgreementDTOGenerator.getResponse();
    private final CreateAgreementRequest request = new CreateAgreementRequest(UUID.randomUUID(), 10);
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Test findAll method")
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void findAll() throws Exception {

        when(get.getAllAgreements()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/agreement"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(value = "Alisa", roles = {"CLIENT"})
    @DisplayName("Test findAllForbidden method")
    void findAllForbidden() throws Exception {

        when(get.getAllAgreements()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/agreement"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test create method")
    void create() throws Exception {

        when(create.createAgreement(request)).thenReturn(response);

        mockMvc.perform(post("/agreement")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number").value(response.getNumber()))
                .andExpect(jsonPath("$.active").value(response.isActive()));
    }

    @Test
    @WithMockUser(value = "Alisa", roles = {"CLIENT"})
    @DisplayName("Test createForbidden method")
    void createForbidden() throws Exception {

        when(create.createAgreement(request)).thenReturn(response);

        mockMvc.perform(post("/agreement")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}