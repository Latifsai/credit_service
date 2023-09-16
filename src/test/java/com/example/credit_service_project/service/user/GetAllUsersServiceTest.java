package com.example.credit_service_project.service.user;

import com.example.credit_service_project.repository.UserRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.UserUtil;
import com.example.credit_service_project.generators.UserDTOGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUsersServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserUtil util;
    @InjectMocks
    GetAllUsersService service;

    @Test
    @DisplayName("Test getAllClients method")
    public void getAllClients() {
        when(repository.findAll()).thenReturn(List.of(EntityCreator.getUser()));
        when(util.convertUserToResponse(EntityCreator.getUser())).thenReturn(UserDTOGenerator.getResponse());

        assertEquals(List.of(UserDTOGenerator.getResponse()), service.getAllClients());
    }

    @Test
    @DisplayName("Test getAllClientsEmpty method")
    public void testGetAllClientsEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), service.getAllClients());
    }

}