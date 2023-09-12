package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(SecurityConfiguration.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private JwtRequestFilter filter;
    @MockBean
    private UserCreateService create;
    @MockBean
    private GetAllUsersService get;
    @MockBean
    private UserSearchService search;
    @MockBean
    private UserUpdateService update;
    @MockBean
    private UserDeleteService delete;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllClients() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void searchClient() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createClient() throws Exception {
        mockMvc.perform(post("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void updateClient() throws Exception {
        mockMvc.perform(put("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
}