package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.account.AccountCreationService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.account.GetAllAccountsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfiguration.class)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
class AccountControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private AccountCreationService create;
    @MockBean
    private GetAllAccountsService get;
    @MockBean
    private AccountUpdateService update;
    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(roles = "MANAGER")
    @Test
    void createNewAccount() throws Exception {
        mockMvc.perform(post("/account"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void getAccountList() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "MANAGER")
    @Test
    void updateAccount() throws Exception {
        mockMvc.perform(put("/account"))
                .andExpect(status().isOk());
    }
}