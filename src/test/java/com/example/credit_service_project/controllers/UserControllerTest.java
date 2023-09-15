package com.example.credit_service_project.controllers;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.generators.UserDTOGenerator;
import com.example.credit_service_project.services.user.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

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

    private final UserResponseDTO response = UserDTOGenerator.getResponse();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void getAllClients() throws Exception {
        when(get.getAllClients()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/users"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAllClientsForbidden() throws Exception {
        when(get.getAllClients()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void searchClient() throws Exception {
        UUID id = UUID.randomUUID();

        when(search.searchUser(id)).thenReturn(response);

        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    void searchClientForbidden() throws Exception {
        UUID id = UUID.randomUUID();

        when(search.searchUser(id)).thenReturn(response);

        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void createClient() throws Exception {
        CreateUserRequest request = new CreateUserRequest("Aziz", "Snow", BigDecimal.valueOf(2500),
                BigDecimal.ZERO, new BigDecimal("1500"), "Johan's Str 34", "john_manager@loewen.de",
                "+49 176 28835002", "MANAGER", "2125");

        when(create.createClient(request)).thenReturn(response);

        mockMvc.perform(post("/users")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.surname").value(response.getSurname()));
    }

    @Test
    void createClientForbidden() throws Exception {
        CreateUserRequest request = new CreateUserRequest("Aziz", "Snow", BigDecimal.valueOf(2500),
                BigDecimal.ZERO, new BigDecimal("1500"), "Johan's Str 34", "john_manager@loewen.de",
                "+49 176 28835002", "MANAGER", "2125");

        when(create.createClient(request)).thenReturn(response);

        mockMvc.perform(post("/users")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void updateClient() throws Exception {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"),
                null, new BigDecimal("2000"), null, null, null, null, null);

        when(update.updateClient(request)).thenReturn(response);

        mockMvc.perform(put("/users")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.surname").isString())
                .andExpect(jsonPath("$.income").value(response.getIncome()));
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"MANAGER"})
    void deleteUser() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(delete).delete(id);

        mockMvc.perform(delete("/users/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserForbidden() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(delete).delete(id);

        mockMvc.perform(delete("/users/{id}", id))
                .andExpect(status().isForbidden());
    }

}