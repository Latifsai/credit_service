package com.example.credit_service_project.serviceTest.operationtests;

import com.example.credit_service_project.repositories.OperationRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.card.CardUpdateService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddOperationServiceImpTest {

    @Mock
    private OperationRepository repository;

    @Mock
    private OperationUtils util;

    @Mock
    private AccountUpdateService updateAccountService;
    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private CardSearchService searchCardService;
    @Mock
    private CardUpdateService updateCardService;
    @InjectMocks
    private AddOperationServiceImp addOperationService;

    @Test
    public void testAddOperationSuccess() {
        var request = DTOOperationCreator.getRequestSpendingOrReplenishment();
        var account = EntityCreator.getAccount();
        var operation = EntityCreator.getOperation();
        var changedAccount = EntityCreator.getAccountAfterOperation();
        var card = EntityCreator.getCard();


        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
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


        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber()))
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