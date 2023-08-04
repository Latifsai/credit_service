package com.example.credit_service_project.serviceTest.clientTest;

import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.service.client.UpdateClientServiceImp;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.ClientUtil;
import com.example.credit_service_project.serviceTest.generators.DTOClientCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateClientServiceImpTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientUtil util;
    @InjectMocks
    private UpdateClientServiceImp service;

    @Test
    public void testAddClientSuccess() {
        var request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"), new BigDecimal("2000"));
        var client = EntityCreator.getClient();
        var updatedClient = EntityCreator.getUpdatedClient();
        when(repository.findById(request.getId())).thenReturn(Optional.of(client));
        when(util.updateClient(client, request)).thenReturn(updatedClient);
        when(util.convertClientToResponse(updatedClient)).thenReturn(DTOClientCreator.getUpdateResponse());

        assertEquals(DTOClientCreator.getUpdateResponse(), service.execute(request));
    }

    @Test
    public void testAddClientNotFoundException() {
        var request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"), new BigDecimal("2000"));
        when(repository.findById(request.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.execute(request));
    }
}