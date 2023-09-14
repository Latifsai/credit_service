package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.generators.UserDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUtilTest {

    @Mock
    private UserUtil util;

    private User user;
    private UserResponseDTO response;

    @BeforeEach
    public void init() {
        user = EntityCreator.getUser();
        response = UserDTOGenerator.getResponse();
    }

    @Test
    void convertAddRequestToEntity() {
        CreateUserRequest request = new CreateUserRequest("name", "surname", BigDecimal.valueOf(1000),
                BigDecimal.ZERO, BigDecimal.valueOf(300), "", "mail", "+777777777", "Client",
                "5487463");

        when(util.convertAddRequestToEntity(request)).thenReturn(user);
        assertEquals(user, util.convertAddRequestToEntity(request));
    }

    @Test
    void convertUserToResponse() {
        when(util.convertUserToResponse(user)).thenReturn(response);
        assertEquals(response, util.convertUserToResponse(user));
    }

    @Test
    void updateClient() {
        UpdateClientRequest request = new UpdateClientRequest(UUID.randomUUID(), BigDecimal.valueOf(1000),
                BigDecimal.ZERO, BigDecimal.valueOf(300), null, null, null, null, null);

        User updatedUser = EntityCreator.getUpdatedUser();
        when(util.updateClient(user, request)).thenReturn(updatedUser);
        assertEquals(updatedUser, util.updateClient(user, request));
    }
}