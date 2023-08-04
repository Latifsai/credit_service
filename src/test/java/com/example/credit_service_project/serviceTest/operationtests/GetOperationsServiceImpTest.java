package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.DTO.operationDTO.GetOperationsListRequest;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.operation.GetOperationsServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
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
class GetOperationsServiceImpTest {

    @Mock
    private OperationRepository repository;

    @Mock
    private OperationUtils util;

    @InjectMocks
    private GetOperationsServiceImp service;

    @Test
    public void testGetServiceSuccess() {
        var request = new GetOperationsListRequest(true);
        var operationList = List.of(EntityCreator.getOperation());
        var response = List.of(DTOOperationCreator.getOperationResponseDTO());

        when(repository.findAllByDebit(request.isDebit())).thenReturn(operationList);
        when(util.convertOperationToResponseDTO(operationList.get(0))).thenReturn(response.get(0));

        var actual = service.execute(request);

        assertEquals(response, actual);
    }

    @Test
    public void testGetServiceEmptyList() {
        var request = new GetOperationsListRequest(true);

        when(repository.findAllByDebit(request.isDebit())).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), service.execute(request));
    }

}