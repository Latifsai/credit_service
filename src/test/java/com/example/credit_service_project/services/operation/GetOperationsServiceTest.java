package com.example.credit_service_project.services.operation;

import com.example.credit_service_project.dto.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.generators.DTOOperationCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.OperationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    public void testGetServiceSuccess() {
        GetBelongsAccountOperationsRequest request = new GetBelongsAccountOperationsRequest(UUID.randomUUID(), null);
        var operationList = List.of(EntityCreator.getOperation());
        var response = List.of(DTOOperationCreator.getOperationResponseDTO());
        Account account = EntityCreator.getAccount();

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(repository.findAllByAccount(account)).thenReturn(operationList);
        when(util.convertOperationToResponseDTO(operationList.get(0))).thenReturn(response.get(0));

        var actual = service.getAllOperations(request);
        assertEquals(response, actual);
    }

}