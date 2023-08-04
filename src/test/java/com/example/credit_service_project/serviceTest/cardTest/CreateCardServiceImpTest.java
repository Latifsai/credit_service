package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
    private AccountRepository accRepository;
    @Spy
    private CardUtil util;

    @InjectMocks
    CreateCardServiceImp createCardService;
    @Test
    public void testCreateCardSuccess() {
        var request = DTOCardCreator.getAddAccountDTORequest();
        var account = EntityCreator.getAccount();
        var card = EntityCreator.getCard();

        when(accRepository.findByIdOrAccountNumber(account.getId(), account.getAccountNumber()))
                .thenReturn(Optional.of(account));
        when(util.convertAddRequestToEntity(request, account)).thenReturn(card);
        when(repository.save(card)).thenReturn(card);

        var actual = createCardService.execute(request);
        assertNotNull(actual);
        assertEquals(DTOCardCreator.getAddResponse(), actual);
    }

    @Test
    public void testCreditCardNotFoundException() {
        var request = DTOCardCreator.getAddAccountDTORequest();
        var account = EntityCreator.getAccount();
        when(accRepository.findByIdOrAccountNumber(eq(account.getId()), anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> createCardService.execute(request));
    }


}