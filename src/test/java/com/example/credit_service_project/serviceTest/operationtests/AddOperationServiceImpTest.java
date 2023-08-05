package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.DTO.operationDTO.SearchAndDeleteOperationRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.OperationRepository;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.card.UpdateCardServiceImp;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.operation.AddOperationServiceImp;
import com.example.credit_service_project.service.utils.OperationUtils;
import com.example.credit_service_project.serviceTest.generators.DTOOperationCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
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
    private OperationUtils util;

    @Mock
    private UpdateAccountServiceImp updateAccountService;
    @Mock
    private SearchAccountsServiceImp searchAccountsService;
    @Mock
    private SearchCardServiceImp searchCardService;
    @Mock
    private UpdateCardServiceImp updateCardService;
    @InjectMocks
    private AddOperationServiceImp addOperationService;

    @Test
    public void testAddOperationSuccess() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishment();
        var account = EntityCreator.getAccount();
        var operation = EntityCreator.getOperation();
        var changedAccount = EntityCreator.getAccountAfterOperation();
        var card = EntityCreator.getCard();


        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.convertAddRequestFunctionalToOperation(request, account))
                .thenReturn(operation);

        when(repository.save(operation)).thenReturn(operation);

        when(util.changeAccountBalance(account, operation)).thenReturn(changedAccount);

        when(searchCardService.findCardByIdAndNumber(request.getCardID(), request.getCardNumber()))
                .thenReturn(Optional.of(card));

        when(util.changerCardBalance(account, card)).thenReturn(EntityCreator.getCardAfterOperation());

        when(util.convertOperationToResponseDTO(operation)).thenReturn(DTOOperationCreator.getOperationResponseDTO());

        var expected = DTOOperationCreator.getOperationResponseDTO();
        var actual = addOperationService.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void testAddOperationNotFoundException() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishment();


        when(searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addOperationService.execute(request));
    }

    @Test
    public void testAddServiceValidation() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishmentWithValidationErrors();
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(5, set.size());
    }

}