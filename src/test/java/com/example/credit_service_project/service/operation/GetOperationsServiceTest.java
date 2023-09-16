package com.example.credit_service_project.service.operation;

import com.example.credit_service_project.dto.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.generators.OperationDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.OperationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOperationsServiceTest {

    @Mock
    private OperationRepository repository;
    @Mock
    private OperationUtils util;
    @Mock
    private AccountSearchService accountSearchService;
    @InjectMocks
    private GetOperationsService service;

    @Test
    @DisplayName("Test getAllOperations method")
    public void getAllOperations() {
        GetBelongsAccountOperationsRequest request = new GetBelongsAccountOperationsRequest(UUID.randomUUID(), null);
        var operationList = List.of(EntityCreator.getOperation());
        var response = List.of(OperationDTOGenerator.getOperationResponseDTO());
        Account account = EntityCreator.getAccount();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(repository.findAllByAccount(account)).thenReturn(operationList);
        when(util.convertOperationToResponseDTO(operationList.get(0))).thenReturn(response.get(0));

        var actual = service.getAllOperations(request);

        assertEquals(response, actual);
        verify(accountSearchService, times(1)).findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        verify(repository, times(1)).findAllByAccount(account);
        verify(util, times(1)).convertOperationToResponseDTO(operationList.get(0));
    }

}