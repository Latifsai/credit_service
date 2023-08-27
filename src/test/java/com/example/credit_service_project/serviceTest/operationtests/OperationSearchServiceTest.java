package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.operation.OperationSearchService;
import com.example.credit_service_project.services.utils.OperationUtils;
import com.example.credit_service_project.serviceTest.generators.DTOOperationCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
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
        var request = new SearchOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true);

        var operation = EntityCreator.getOperation();
        var response = DTOOperationCreator.getOperationResponseDTO();

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.of(operation));

        when(util.convertOperationToResponseDTO(operation)).thenReturn(response);

        assertEquals(response, service.searchOperation(request));

    }

    @Test
    public void testSearchServiceNotFoundException() {
        var request = new SearchOperationRequest(
                UUID.fromString("11117777-9999-1111-b491-426655440000"),
                true);

        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.searchOperation(request));
    }

    @Test
    public void testSearchServiceValidation() {
        var request = new SearchOperationRequest(null, true);
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(1, set.size());
    }
}