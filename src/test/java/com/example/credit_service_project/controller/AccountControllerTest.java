package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.dto.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.generators.AccountDTOGenerator;
import com.example.credit_service_project.service.account.AccountCreationService;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.account.GetAllAccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AccountControllerTest {

    @MockBean
    private AccountCreationService create;
    @MockBean
    private GetAllAccountsService get;
    @MockBean
    private AccountUpdateService update;
    @Autowired
    private MockMvc mockMvc;

    private final AccountResponseDTO response = AccountDTOGenerator.getResponse();
    private final ObjectMapper mapper = new ObjectMapper();
    private final CreateAccountRequestDTO request = new CreateAccountRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
            "United States", BigDecimal.valueOf(3000), "USD", 10, 7);

    private final UpdateAccountRequest updateRequest = new UpdateAccountRequest(null, null, null,
            null, null, null, null, null);


    @BeforeEach
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Test createNewAccount method")
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void createNewAccount() throws Exception {


        when(create.createAccount(request)).thenReturn(response);

        mockMvc.perform(post("/account")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value(response.getAccountNumber()))
                .andExpect(jsonPath("$.balance").value(response.getBalance()));
    }

    @Test
    @DisplayName("Test createNewAccountUnauthorized method")
    @WithMockUser(value = "Alisa", roles = {"CLIENT"})
    void createNewAccountUnauthorized() throws Exception {

        when(create.createAccount(request)).thenReturn(response);

        mockMvc.perform(post("/account")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test getAccountList method")
    void getAccountList() throws Exception {

        when(get.getAllAccounts()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/account"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].balance").value(response.getBalance()));
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    @DisplayName("Test updateAccount method")
    void updateAccount() throws Exception {

        var updatedResponse = AccountDTOGenerator.getUpdatedDTOResponse();
        when(update.updateAccount(updateRequest)).thenReturn(updatedResponse);

        mockMvc.perform(put("/account")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(updatedResponse.getBalance()))
                .andExpect(jsonPath("$.balance").value(updatedResponse.getBalance()));
    }

    @Test
    @WithMockUser(value = "Alisa", roles = {"CLIENT"})
    @DisplayName("Test updateAccountUnauthorized method")
    void updateAccountUnauthorized() throws Exception {
        var updatedResponse = AccountDTOGenerator.getUpdatedDTOResponse();
        when(update.updateAccount(updateRequest)).thenReturn(updatedResponse);

        mockMvc.perform(put("/account")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateRequest)))
                .andExpect(status().isForbidden());
    }
}