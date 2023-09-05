package com.example.credit_service_project.services.user;

import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.UserUtil;
import com.example.credit_service_project.services.generators.DTOClientCreator;
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
    public void testGetAllClientsSuccess() {
        when(repository.findAll()).thenReturn(List.of(EntityCreator.getClient()));
        when(util.convertClientToResponse(EntityCreator.getClient())).thenReturn(DTOClientCreator.getResponse());

        assertEquals(List.of(DTOClientCreator.getResponse()), service.getAllClients());
    }

    @Test
    public void testGetAllClientsEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), service.getAllClients());
    }

}