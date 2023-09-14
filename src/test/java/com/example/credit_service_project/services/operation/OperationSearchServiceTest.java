package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.entity.Operation;
import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.generators.OperationDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
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
class OperationSearchServiceTest {

    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;

    @InjectMocks
    private OperationSearchService service;

    @Test
    public void testSearchServiceSuccess() {
        UUID id = UUID.fromString("11117777-9999-1111-b491-426655440000");

        Operation operation = EntityCreator.getOperation();
        OperationResponseDTO response = OperationDTOGenerator.getOperationResponseDTO();

        when(repository.findById(id)).thenReturn(Optional.of(operation));
        when(util.convertOperationToResponseDTO(operation)).thenReturn(response);

        assertEquals(response, service.searchOperation(id));
    }

    @Test
    public void testSearchServiceNotFoundException() {
        UUID id = UUID.fromString("11117777-9999-1111-b491-426655440000");
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.searchOperation(id));
    }

}