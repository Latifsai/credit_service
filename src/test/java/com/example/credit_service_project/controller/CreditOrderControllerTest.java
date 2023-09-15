package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.generators.CreditOrderedGenerator;
import com.example.credit_service_project.service.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.service.creditOrder.CreditOrderUpdateService;
import com.example.credit_service_project.service.creditOrder.DecisionOrderService;
import com.example.credit_service_project.service.creditOrder.GetAllCreditOrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class CreditOrderControllerTest {

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

    private final CreditOrderResponseDTO response = CreditOrderedGenerator.getResponse();
    private final CreateCreditOrderResponseDTO creationResponse = CreditOrderedGenerator.getCreateResponse();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void create() throws Exception {
        CreateCreditOrderRequestDTO request = new CreateCreditOrderRequestDTO(1L, UUID.randomUUID(),
                null, 10);

        when(create.createCreditOrder(request)).thenReturn(creationResponse);

        mockMvc.perform(post("/order")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number").value(response.getNumber()))
                .andExpect(jsonPath("$.amount").value(response.getAmount()))
                .andExpect(jsonPath("$.creditOrderStatus").exists());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void createForbidden() throws Exception {
        CreateCreditOrderRequestDTO request = new CreateCreditOrderRequestDTO(1L, UUID.randomUUID(),
                null, 10);

        when(create.createCreditOrder(request)).thenReturn(creationResponse);

        mockMvc.perform(post("/order")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void getAll() throws Exception {

        when(getAllOrders.getAllCreditOrders()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/order"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].number").value(response.getNumber()));
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void getAllForbidden() throws Exception {

        when(getAllOrders.getAllCreditOrders()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/order"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void update() throws Exception {
        UpdateCreditOrderRequestDTO request = new UpdateCreditOrderRequestDTO(UUID.fromString("c3009377-3b57-4965-8540-69d56fce34f4"), null,
                null, null, CreditOrderStatus.CLOSED);

        when(update.updateCreditOrder(request)).thenReturn(response);

        mockMvc.perform(put("/order")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(response.getAmount()))
                .andExpect(jsonPath("$.number").value(response.getNumber()))
                .andExpect(jsonPath("$.creditOrderStatus").exists());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void updateForbidden() throws Exception {
        UpdateCreditOrderRequestDTO request = new UpdateCreditOrderRequestDTO(UUID.fromString("c3009377-3b57-4965-8540-69d56fce34f4"), null,
                null, null, CreditOrderStatus.CLOSED);

        when(update.updateCreditOrder(request)).thenReturn(response);

        mockMvc.perform(put("/order")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void decision() throws Exception {

        when(decisionOrderService.acceptOrder()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(put("/order/review"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void decisionForbidden() throws Exception {

        when(decisionOrderService.acceptOrder()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(put("/order/review"))
                .andExpect(status().isForbidden());
    }
}