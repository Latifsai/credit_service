package com.example.credit_service_project.serviceTest.managerTest;

import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.manager.DeleteManagerServiceImp;
import com.example.credit_service_project.service.utils.ManagerUtil;
import com.example.credit_service_project.serviceTest.generators.DTOManagerCreator;
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
class DeleteManagerServiceImpTest {

    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerUtil util;
    @InjectMocks
    private DeleteManagerServiceImp service;

    @Test
    public void testDeleteManagerServiceSuccess() {
        UUID id = UUID.randomUUID();
        var manager = EntityCreator.getManager();

        when(repository.findById(id)).thenReturn(Optional.of(manager));
        when(util.convertManagerToResponse(manager)).thenReturn(DTOManagerCreator.getResponseDTO());

        assertEquals(DTOManagerCreator.getResponseDTO(), service.execute(id));
    }

    @Test
    public void testDeleteManagerServiceNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(id));
    }
}