package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCardServiceImpTest {
    @Mock
    private CardRepository repository;
    @Mock
    private SearchAccountsServiceImp searchAccountsService;
    @Mock
    private CardUtil util;

    @InjectMocks
    private CreateCardServiceImp createCardService;

    @Test
    public void testCreateCardSuccess() {
        var request = DTOCardCreator.getAddAccountDTORequest();
        var account = EntityCreator.getAccount();
        var card = EntityCreator.getCard();

        when(searchAccountsService.findAccountByIdOrNumber(account.getId(), account.getAccountNumber()))
                .thenReturn(Optional.of(account));
        when(util.convertAddRequestToEntity(request, account)).thenReturn(card);
        when(repository.save(card)).thenReturn(card).thenReturn(card);
        when(util.convertCardToAddDTOResponse(card)).thenReturn(DTOCardCreator.getCardResponse());
        var actual = createCardService.execute(request);
        assertEquals(DTOCardCreator.getCardResponse(), actual);
    }

    @Test
    public void testCreditCardNotFoundException() {
        var request = DTOCardCreator.getAddAccountDTORequest();
        var account = EntityCreator.getAccount();
        when(searchAccountsService.findAccountByIdOrNumber(eq(account.getId()), anyString()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> createCardService.execute(request));
    }

    @Test
    public void testCreditCardValidation() {
        var request = DTOCardCreator.getAddAccountDTORequestWithValidationErrors();

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(4, set.size());
    }
}