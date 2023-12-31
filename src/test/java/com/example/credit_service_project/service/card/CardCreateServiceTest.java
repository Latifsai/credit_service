package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import jakarta.validation.Validation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.example.credit_service_project.entity.enums.PaymentSystem.VISA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName(value = "Test card create service")
@ExtendWith(MockitoExtension.class)
class CardCreateServiceTest {
    @Mock
    private CardRepository repository;
    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private CardUtil util;
    @InjectMocks
    private CardCreateService createCardService;
    @Test
    @DisplayName(value = "Test card create method")
    public void testCreateCard() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);

        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();

        when(accountSearchService.findAccountByIdOrNumber(account.getId(), account.getAccountNumber()))
                .thenReturn(account);
        when(util.convertCreateRequestToEntity(request, account)).thenReturn(card);
        when(repository.save(card)).thenReturn(card).thenReturn(card);
        when(util.convertCardToAddDTOResponse(card)).thenReturn(CardDTOGenerator.getCardResponse());
        CardResponseDTO actual = createCardService.createCard(request);

        assertEquals(CardDTOGenerator.getCardResponse(), actual);
        verify(accountSearchService,times(1)).findAccountByIdOrNumber(account.getId(), account.getAccountNumber());
        verify(util,times(1)).convertCreateRequestToEntity(request, account);
        verify(repository,times(1)).save(card);
        verify(util,times(1)).convertCardToAddDTOResponse(card);

    }

    @Test
    @DisplayName(value = "Test card create throw NotFoundException method")
    public void testCreditCardNotFoundException() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);
        Account account = EntityCreator.getAccount();

        when(accountSearchService.findAccountByIdOrNumber(account.getId(), request.getAccountNumber()))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> createCardService.createCard(request));
    }

    @Test
    @DisplayName(value = "Test card create CardValidation method")
    public void testCreditCardValidation() {
        Card card = EntityCreator.getCardForValidation();

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(card);
        assertEquals(2, set.size());
    }
}