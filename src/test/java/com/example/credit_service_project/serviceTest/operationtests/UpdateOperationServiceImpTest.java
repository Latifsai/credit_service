package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.errors.exceptions.OperationException;
import com.example.credit_service_project.service.operation.UpdateOperationServiceImp;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateOperationServiceImpTest {

    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;
    @InjectMocks
    UpdateOperationServiceImp service;

    @Test
    public void testUpdateServiceSuccess() {
        var request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true,
                OperationType.MONTHLY_PAYMENT,
                "Mortgage payment"
        );

        var operation = EntityCreator.getOperation();

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.of(operation));

        when(util.updateOperation(operation, request))
                .thenReturn(EntityCreator.getUpdatedOperation());

        when(util.convertUpdatedOperationToUpdateResponse(EntityCreator.getUpdatedOperation()))
                .thenReturn(DTOOperationCreator.getUpdateResponse());

        assertEquals(DTOOperationCreator.getUpdateResponse(), service.execute(request));
    }

    @Test
    public void testUpdateServiceNotFoundException() {
        var request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true,
                OperationType.MONTHLY_PAYMENT,
                "Mortgage payment"
        );


        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));
    }

    @Test
    public void testUpdateServiceOperationException() {
        var request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true,
                OperationType.REPLENISHMENT,
                "Mortgage payment"
        );

        var operation = EntityCreator.getOperation();

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.of(operation));

        when(util.updateOperation(operation, request)).thenThrow(OperationException.class);

        assertThrows(OperationException.class, () -> service.execute(request));
    }


}