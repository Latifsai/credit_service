package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.generators.DTOAccountCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName(value = "Test account search service")
@ExtendWith(MockitoExtension.class)
public class SearchAccountImpTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountUtil util;
    @InjectMocks
    private AccountSearchService service;

    @DisplayName(value = "Test search account")
    @Test
    public void testSearchAccount() {
        SearchAccountRequest request = new SearchAccountRequest(UUID.randomUUID(), "A10B3U3OI9");
        Account account = EntityCreator.getAccount();

        when(repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.convertAccountToAddResponse(account)).thenReturn(DTOAccountCreator.createDTOResponse());

        assertEquals(DTOAccountCreator.createDTOResponse(), service.searchAccount(request));
    }

    @DisplayName(value = "Test search account NotFoundException")
    @Test
    public void testSearchAccountNotFoundException() {
        SearchAccountRequest request = new SearchAccountRequest(UUID.randomUUID(), null);

        when(repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber()))
                .thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> service.searchAccount(request));
    }

}
