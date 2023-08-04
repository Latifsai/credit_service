package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.DTO.operationDTO.SearchAndDeleteOperationRequest;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.operation.SearchOperationServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.serviceTest.generators.DTOOperationCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchOperationServiceImpTest {

    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;

    @InjectMocks
    SearchOperationServiceImp service;

    @Test
    public void testSearchServiceSuccess() {
        var request = new SearchAndDeleteOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true);

        var operation = EntityCreator.getOperation();
        var response = DTOOperationCreator.getSearchResponse();

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.of(operation));

        when(util.convertOperationToSearchResponse(operation)).thenReturn(response);

        assertEquals(response, service.execute(request));

    }

    @Test
    public void testSearchServiceNotFoundException() {
        var request = new SearchAndDeleteOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true);

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));

    }
}