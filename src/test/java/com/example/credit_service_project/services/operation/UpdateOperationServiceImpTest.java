//package com.example.credit_service_project.services.operation;
//
//import com.example.credit_service_project.DTO.operationDTO.UpdateOperationsRequest;
//import com.example.credit_service_project.entity.enums.OperationType;
//import com.example.credit_service_project.repositories.OperationRepository;
//import com.example.credit_service_project.validation.exceptions.OperationException;
//import com.example.credit_service_project.services.utils.OperationUtils;
//import com.example.credit_service_project.services.generators.DTOOperationCreator;
//import com.example.credit_service_project.services.generators.EntityCreator;
//import jakarta.validation.Validation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UpdateOperationServiceImpTest {
//
//    @Mock
//    private OperationRepository repository;
//    @Mock
//    private OperationUtils util;
//    @InjectMocks
//    OperationUpdateService service;
//
//    @Test
//    public void testUpdateServiceSuccess() {
//        var request = new UpdateOperationsRequest(
//                UUID.fromString("11117777-9999-1111-b491-426655440000"),
//                true,
//                OperationType.MONTHLY_PAYMENT,
//                "Mortgage payment"
//        );
//
//        var operation = EntityCreator.getOperation();
//
//        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
//                .thenReturn(Optional.of(operation));
//
//        when(util.updateOperation(operation, request))
//                .thenReturn(EntityCreator.getUpdatedOperation());
//
//        when(util.convertOperationToResponseDTO(EntityCreator.getUpdatedOperation()))
//                .thenReturn(DTOOperationCreator.getUpdateOperationResponseDTO());
//
//        assertEquals(DTOOperationCreator.getUpdateOperationResponseDTO(), service.updateOperation(request));
//    }
//
//    @Test
//    public void testUpdateServiceNotFoundException() {
//        var request = new UpdateOperationsRequest(
//                UUID.fromString("11117777-9999-1111-b491-426655440000"),
//                true,
//                OperationType.MONTHLY_PAYMENT,
//                "Mortgage payment"
//        );
//
//
//        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
//                .thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> service.updateOperation(request));
//    }
//
//    @Test
//    public void testUpdateServiceOperationException() {
//        var request = new UpdateOperationsRequest(
//                UUID.fromString("11117777-9999-1111-b491-426655440000"),
//                true,
//                OperationType.REPLENISHMENT,
//                "Mortgage payment"
//        );
//
//        var operation = EntityCreator.getOperation();
//
//        when(repository.findByIdAndDebit(request.getId(), request.isDebit()))
//                .thenReturn(Optional.of(operation));
//
//        when(util.updateOperation(operation, request)).thenThrow(OperationException.class);
//
//        assertThrows(OperationException.class, () -> service.updateOperation(request));
//    }
//
//
//    @Test
//    public void testUpdateServiceValidation() {
//        var request = new UpdateOperationsRequest(
//                null,
//                true,
//                OperationType.REPLENISHMENT,
//                "Mortgage payment"
//        );
//
//        var validator = Validation.buildDefaultValidatorFactory().getValidator();
//        var set = validator.validate(request);
//        assertEquals(1, set.size());
//    }
//}