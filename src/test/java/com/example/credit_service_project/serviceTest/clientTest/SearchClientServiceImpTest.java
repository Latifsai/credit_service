package com.example.credit_service_project.serviceTest.clientTest;

import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.services.client.SearchClientServiceImp;
import com.example.credit_service_project.services.utils.ClientUtil;
import com.example.credit_service_project.serviceTest.generators.DTOClientCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchClientServiceImpTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientUtil util;
    @InjectMocks
    private SearchClientServiceImp service;

    @Test
    public void testAddClientSuccess() {
        UUID id = UUID.randomUUID();
        var client = EntityCreator.getClient();

        when(repository.findById(id)).thenReturn(Optional.of(client));
        when(util.convertClientToResponse(client)).thenReturn(DTOClientCreator.getResponse());

        assertEquals(DTOClientCreator.getResponse(), service.execute(id));
    }

    @Test
    public void testAddClientNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.execute(id));
    }


}