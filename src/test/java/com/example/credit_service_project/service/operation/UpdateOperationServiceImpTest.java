package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.UpdateOperationsRequest;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.entity.enums.OperationType;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.generators.OperationDTOGenerator;
import com.example.credit_service_project.service.utils.OperationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOperationServiceImpTest {

    @Mock
    private PaymentProcessingService addOperationService;

    @Mock
    private OperationSearchService searchOperationService;
    @Mock
    private OperationUtils util;
    @InjectMocks
    private OperationUpdateService service;

    @Test
    public void testUpdateServiceSuccess() {
        UpdateOperationsRequest request = new UpdateOperationsRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                OperationType.EARLY_REPAYMENT,
                "EARLY_REPAYMENT"
        );

        Operation operation = EntityCreator.getOperation();
        Operation updatedOperation = EntityCreator.getUpdatedOperation();

        when(searchOperationService.findById(request.getId())).thenReturn(operation);
        when(util.updateOperation(operation, request)).thenReturn(updatedOperation);
        when(addOperationService.saveOperation(updatedOperation)).thenReturn(updatedOperation);
        when(util.convertOperationToResponseDTO(updatedOperation)).thenReturn(OperationDTOGenerator.getUpdateOperationResponseDTO());

        assertEquals(OperationDTOGenerator.getUpdateOperationResponseDTO(), service.updateOperation(request));
        verify(addOperationService, times(1)).saveOperation(updatedOperation);
    }
}