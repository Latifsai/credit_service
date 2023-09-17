package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PaymentsOperationRequest;
import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.generators.OperationDTOGenerator;
import com.example.credit_service_project.service.operation.OperationUpdateService;
import com.example.credit_service_project.service.operation.PaymentProcessingService;
import com.example.credit_service_project.service.operation.ReplenishmentAndEarlyPaymentOperationService;
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

import static com.example.credit_service_project.entity.enums.OperationType.EARLY_REPAYMENT;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OperationControllerTest {

    @MockBean
    private PaymentProcessingService createPaymentOperation;
    @MockBean
    private ReplenishmentAndEarlyPaymentOperationService replenishmentAndEarlyPaymentOperation;
    @MockBean
    private OperationUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final OperationResponseDTO response = OperationDTOGenerator.getOperationResponseDTO();
    private final OperationResponseDTO replenishmentResponse = OperationDTOGenerator.getOperationResponseREPLENISHMENT();
    private final OperationResponseDTO updateResponse = OperationDTOGenerator.getUpdateOperationResponseDTO();

    @Test
    @DisplayName("Test handlePayments method")
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void handlePayments() throws Exception {

        when(createPaymentOperation.handlePayments()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(post("/operation"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountNumber").exists());
    }

    @Test
    @DisplayName("Test handlePaymentsForbidden method")
    void handlePaymentsForbidden() throws Exception {

        when(createPaymentOperation.handlePayments()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(post("/operation"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test addNewOperation method")
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void addNewOperation() throws Exception {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(1000), OperationType.REPLENISHMENT, "REPLENISHMENT");

        when(replenishmentAndEarlyPaymentOperation.performOperation(request)).thenReturn(replenishmentResponse);

        mockMvc.perform(post("/operation/elective")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value(response.getAccountNumber()))
                .andExpect(jsonPath("$.balance").value(response.getBalance()));
    }

    @Test
    @DisplayName("Test addNewOperationForbidden method")
    void addNewOperationForbidden() throws Exception {
        PaymentsOperationRequest request = new PaymentsOperationRequest(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                null, BigDecimal.valueOf(1000), OperationType.REPLENISHMENT, "REPLENISHMENT");

        when(replenishmentAndEarlyPaymentOperation.performOperation(request)).thenReturn(replenishmentResponse);

        mockMvc.perform(post("/operation/elective")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test update method")
    @WithMockUser(value = "Oleg", roles = "MANAGER")
    void update() throws Exception {
        UpdateOperationsRequest request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"), EARLY_REPAYMENT, "EARLY_REPAYMENT");

        when(update.updateOperation(request)).thenReturn(updateResponse);

        mockMvc.perform(put("/operation")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value(updateResponse.getAccountNumber()))
                .andExpect(jsonPath("$.balance").value(updateResponse.getBalance()))
                .andExpect(jsonPath("$.sum").value(updateResponse.getSum()))
                .andExpect(jsonPath("$.type").exists());
    }

    @Test
    @DisplayName("Test updateForbidden method")
    void updateForbidden() throws Exception {
        UpdateOperationsRequest request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"), EARLY_REPAYMENT, "EARLY_REPAYMENT");

        when(update.updateOperation(request)).thenReturn(updateResponse);

        mockMvc.perform(put("/operation")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}