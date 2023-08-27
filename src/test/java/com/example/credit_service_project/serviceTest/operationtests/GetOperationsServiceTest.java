package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.operation.GetOperationsService;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.serviceTest.generators.DTOOperationCreator;
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
class GetOperationsServiceTest {

    @Mock
    private OperationRepository repository;

    @Mock
    private OperationUtils util;

    @InjectMocks
    private GetOperationsService service;

    @Test
    public void testGetServiceSuccess() {
        var operationList = List.of(EntityCreator.getOperation());
        var response = List.of(DTOOperationCreator.getOperationResponseDTO());

        when(repository.findAll()).thenReturn(operationList);

        when(util.convertOperationToResponseDTO(operationList.get(0))).thenReturn(response.get(0));

        var actual = service.getAllOperations();

        assertEquals(response, actual);
    }

    @Test
    public void testGetServiceEmptyList() {

        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), service.getAllOperations());
    }

}