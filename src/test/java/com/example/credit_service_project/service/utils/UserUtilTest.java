package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.UserDTOGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserUtilTest {

    @InjectMocks
    private UserUtil util;

    private User user;
    private UserResponseDTO response;

    @BeforeEach
    public void init() {
        user = EntityCreator.getUser();
        response = UserDTOGenerator.getResponse();
    }

    @Test
    @DisplayName("Test convertAddRequestToEntity method")
    void convertAddRequestToEntity() {
        CreateUserRequest request = new CreateUserRequest("name", "surname", BigDecimal.valueOf(1000),
                BigDecimal.ZERO, BigDecimal.valueOf(300), "", "mail", "+777777777", "Client",
                "5487463");

        assertNotNull(util.convertAddRequestToEntity(request));
    }

    @Test
    @DisplayName("Test convertUserToResponse method")
    void convertUserToResponse() {
        assertNotNull(util.convertUserToResponse(user));
    }

    @Test
    @DisplayName("Test updateClient method")
    void updateClient() {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), BigDecimal.valueOf(4000),
                BigDecimal.ZERO, BigDecimal.valueOf(2000), null, null, null, null, null);

        assertNotNull(util.updateClient(user, request));
    }
}