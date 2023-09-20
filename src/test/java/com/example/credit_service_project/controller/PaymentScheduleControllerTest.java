package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.paymentDTO.GetBelongsPaymentsResponse;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentsBelongsToAccountRequest;
import com.example.credit_service_project.generators.PaymentDTOGenerator;
import com.example.credit_service_project.service.paymentSchedule.GetBelongsToAccountPaymentsService;
import com.example.credit_service_project.service.paymentSchedule.GetNearestPaymentService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PaymentScheduleControllerTest {

    @MockBean
    private GetBelongsToAccountPaymentsService getBelongsToTheAccountPaymentsList;
    @MockBean
    private GetNearestPaymentService getNearestPayment;
    @Autowired
    private MockMvc mockMvc;

    private final PaymentResponseDTO response = PaymentDTOGenerator.getPaymentResponseDTO();
    private final GetBelongsPaymentsResponse getBelongsPaymentsResponse = new GetBelongsPaymentsResponse(UUID.randomUUID(),
            "A43A1A13A5A", Collections.singletonList(response));
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Test getNearestPayment method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void getNearestPayment() throws Exception {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), "A43A1A13A5A");

        when(getNearestPayment.getNearestPayment(request)).thenReturn(response);

        mockMvc.perform(get("/payments/nearest")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.monthlyPayment").value(response.getMonthlyPayment()))
                .andExpect(jsonPath("$.paid").value(response.isPaid()));
    }

    @Test
    @DisplayName("Test getNearestPaymentForbidden method")
    void getNearestPaymentForbidden() throws Exception {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), "A43A1A13A5A");

        when(getNearestPayment.getNearestPayment(request)).thenReturn(response);

        mockMvc.perform(get("/payments/nearest")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    @DisplayName("Test getBelongsPaymentsResponse method")
    void getBelongsPaymentsResponse() throws Exception {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);

        when(getBelongsToTheAccountPaymentsList.getBelongsToAccountPayments(request)).thenReturn(getBelongsPaymentsResponse);

        mockMvc.perform(get("/payments")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.accountID").exists())
                .andExpect(jsonPath("$.accountNumber").exists())
                .andExpect(jsonPath("$.list", hasSize(1)));
    }

    @Test
    @DisplayName("Test getBelongsPaymentsResponseForbidden method")
    void getBelongsPaymentsResponseForbidden() throws Exception {
        PaymentsBelongsToAccountRequest request = new PaymentsBelongsToAccountRequest(UUID.randomUUID(), null);

        when(getBelongsToTheAccountPaymentsList.getBelongsToAccountPayments(request)).thenReturn(getBelongsPaymentsResponse);

        mockMvc.perform(get("/payments")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}