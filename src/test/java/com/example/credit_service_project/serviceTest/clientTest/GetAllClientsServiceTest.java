package com.example.credit_service_project.serviceTest.clientTest;

import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.client.GetAllClientsService;
import com.example.credit_service_project.service.utils.ClientUtil;
import com.example.credit_service_project.serviceTest.generators.DTOClientCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
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
class GetAllClientsServiceTest {

    @Mock
    private ClientRepository repository;
    @Mock
    private ClientUtil util;

    @InjectMocks
    GetAllClientsService service;

    @Test
    public void testGetAllClientsSuccess() {
        when(repository.findAll()).thenReturn(List.of(EntityCreator.getClient()));
        when(util.convertClientToResponse(EntityCreator.getClient())).thenReturn(DTOClientCreator.getResponse());

        assertEquals(List.of(DTOClientCreator.getResponse()), service.execute());
    }

    @Test
    public void testGetAllClientsEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), service.execute());
    }

}