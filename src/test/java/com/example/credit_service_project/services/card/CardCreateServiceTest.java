package com.example.credit_service_project.services.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.generators.DTOCardCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CardUtil;
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
import static org.mockito.Mockito.when;

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
    @DisplayName(value = "Test card create")
    @Test
    public void testCreateCard() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);

        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();

        when(accountSearchService.findAccountByIdOrNumber(account.getId(), account.getAccountNumber()))
                .thenReturn(account);
        when(util.convertCreateRequestToEntity(request, account)).thenReturn(card);
        when(repository.save(card)).thenReturn(card).thenReturn(card);
        when(util.convertCardToAddDTOResponse(card)).thenReturn(DTOCardCreator.getCardResponse());
        CardResponseDTO actual = createCardService.createCard(request);
        assertEquals(DTOCardCreator.getCardResponse(), actual);
    }

    @DisplayName(value = "Test card create throw NotFoundException")
    @Test
    public void testCreditCardNotFoundException() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", 4, true, VISA);
        Account account = EntityCreator.getAccount();

        when(accountSearchService.findAccountByIdOrNumber(account.getId(), request.getAccountNumber()))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> createCardService.createCard(request));
    }

    @DisplayName(value = "Test card create CardValidation")
    @Test
    public void testCreditCardValidation() {
        Card card = EntityCreator.getCardForValidation();

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(card);
        assertEquals(2, set.size());
    }
}