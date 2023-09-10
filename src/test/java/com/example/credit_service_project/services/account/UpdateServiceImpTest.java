package com.example.credit_service_project.services.account;

import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.services.generators.DTOAccountCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountStatusException;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName(value = "Test Account Update Service")
@ExtendWith(MockitoExtension.class)
public class UpdateServiceImpTest {
    @Mock
    private AccountSearchService searchService;
    @Mock
    private AccountCreationService creationService;
    @Mock
    private AccountUtil util;
    @InjectMocks
    private AccountUpdateService service;

    @Test
    @DisplayName(value = "Test update account")
    public void upgradeAccountService() {
        UpdateAccountRequest request = new UpdateAccountRequest(UUID.randomUUID(), null, null,
                null, null, BigDecimal.valueOf(5000), null, null);

        Account account = EntityCreator.getAccount();
        Account updatedAccount = EntityCreator.getUpgratedAccount();

        when(searchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(util.updateAccount(account, request)).thenReturn(EntityCreator.getUpgratedAccount());
        when(creationService.saveAccount(updatedAccount)).thenReturn(updatedAccount);
        when(util.convertAccountToAddResponse(updatedAccount)).thenReturn(DTOAccountCreator.getUpdatedDTOResponse());

        assertEquals(DTOAccountCreator.getUpdatedDTOResponse(), service.updateAccount(request));
    }

    @Test
    @DisplayName(value = "Get NotFoundException by search account")
    public void upgradeTestNotFoundException() {
        UpdateAccountRequest request = new UpdateAccountRequest(UUID.randomUUID(), null, null,
                null, null, BigDecimal.valueOf(5000), null, null);

        when(searchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> service.updateAccount(request));
    }

    @Test
    @DisplayName(value = "Get AccountStatusException by update account")
    public void upgradeTestAccountStatusException() {
        UpdateAccountRequest request = new UpdateAccountRequest(UUID.randomUUID(), null, null,
                null, null, BigDecimal.valueOf(5000), null, null);
        Account account = EntityCreator.getAccount();
        account.setStatus(AccountStatus.BLOCKED);

        when(searchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);

        assertThrows(AccountStatusException.class, () -> service.updateAccount(request));
    }

}
