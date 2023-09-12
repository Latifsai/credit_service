package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.paymentSchedule.GetBelongsToAccountPaymentsService;
import com.example.credit_service_project.services.paymentSchedule.GetNearestPaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentScheduleController.class)
@Import(SecurityConfiguration.class)
class PaymentScheduleControllerTest {

    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private GetBelongsToAccountPaymentsService getBelongsToTheAccountPaymentsList;
    @MockBean
    private GetNearestPaymentService getNearestPayment;
    @Autowired
    private MockMvc mockMvc;

    @WithAnonymousUser
    @Test
    void search() throws Exception {
        mockMvc.perform(get("/payments/search"))
                .andExpect(status().isOk());
    }

    @WithAnonymousUser
    @Test
    void getBelongsPaymentsResponse() throws Exception {
        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk());
    }
}