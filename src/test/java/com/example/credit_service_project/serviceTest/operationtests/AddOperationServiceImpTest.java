package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.operation.AddOperationServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.serviceTest.generators.DTOOperationCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddOperationServiceImpTest {

    @Mock
    private OperationRepository repository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationUtils util;
    @InjectMocks
    AddOperationServiceImp addOperationService;

    @Test
    public void testAddOperationSuccess() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishment();
        var account = EntityCreator.getAccount();
        var operation = EntityCreator.getOperation();
        var changedAccount = EntityCreator.getAccountAfterOperation();

        when(accountRepository.findByAccountNumber(request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.convertAddRequestFunctionalToOperation(request, account))
                .thenReturn(operation);

        when(repository.save(operation)).thenReturn(operation);

        when(util.changeBalance(account, operation))
                .thenReturn(changedAccount);

        when(accountRepository.save(changedAccount)).thenReturn(changedAccount);

        when(util.convertOperationToAddResponse(operation)). thenReturn(DTOOperationCreator.getAddResponse());

        var expected = DTOOperationCreator.getAddResponse();
        var actual = addOperationService.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void testAddOperationNotFoundException() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishment();


        when(accountRepository.findByAccountNumber(request.getAccountNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addOperationService.execute(request));
    }

}