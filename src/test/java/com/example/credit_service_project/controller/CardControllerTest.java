package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.service.card.CardCreateService;
import com.example.credit_service_project.service.card.CardUpdateService;
import com.example.credit_service_project.service.card.GetAllCardsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static com.example.credit_service_project.entity.enums.PaymentSystem.VISA;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class CardControllerTest {

    @MockBean
    private CardCreateService create;
    @MockBean
    private GetAllCardsService get;
    @MockBean
    private CardUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    private final CardResponseDTO response = CardDTOGenerator.getCardResponse();
    private final CardResponseDTO updatedResponse = CardDTOGenerator.getUpdatedCardResponse();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test createCard method")
    void createCard() throws Exception {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);

        when(create.createCard(request)).thenReturn(response);

        mockMvc.perform(post("/card")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test createCardForbidden method")
    void createCardForbidden() throws Exception {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);

        when(create.createCard(request)).thenReturn(response);

        mockMvc.perform(post("/card")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test getCards method")
    void getCards() throws Exception {

        when(get.getAllCards()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/card"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].iban").value(response.getIBAN()));
    }

    @Test
    @DisplayName("Test getCardsForbidden method")
    void getCardsForbidden() throws Exception {

        when(get.getAllCards()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/card"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test updateCard method")
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void updateCard() throws Exception {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"), "", null);

        when(update.saveUpdateCard(request)).thenReturn(updatedResponse);

        mockMvc.perform(put("/card")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value(updatedResponse.getCardNumber()))
                .andExpect(jsonPath("$.iban").value(updatedResponse.getIBAN()))
                .andExpect(jsonPath("$.balance").value(updatedResponse.getBalance()));
    }

    @Test
    @DisplayName("Test updateCardForbidden method")
    void updateCardForbidden() throws Exception {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"), "", null);

        when(update.saveUpdateCard(request)).thenReturn(updatedResponse);

        mockMvc.perform(put("/card")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}