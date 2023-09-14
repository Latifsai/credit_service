package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.dto.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.services.generators.DTOAccountCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountUtilTest {

    @Mock
    private AccountUtil accountUtil;

    @Test
    void convertAddRequestToAccount() {
        CreateAccountRequestDTO request = new CreateAccountRequestDTO(UUID.fromString("de7187a4-1448-4f94-a863-ecc8c817a376"),
                "United States", BigDecimal.valueOf(3000), "USD", 10, 4);
        User user = EntityCreator.getUser();
        Account account = EntityCreator.getAccount();
        when(accountUtil.convertAddRequestToAccount(request, user)).thenReturn(account);
        Account result = accountUtil.convertAddRequestToAccount(request, user);
        assertEquals(account, result);

    }

    @Test
    void convertAccountToAddResponse() {
        Account account = EntityCreator.getAccount();
        AccountResponseDTO response = DTOAccountCreator.createDTOResponse();

        when(accountUtil.convertAccountToAddResponse(account)).thenReturn(response);

        AccountResponseDTO result = accountUtil.convertAccountToAddResponse(account);
        assertEquals(response, result);
    }

    @Test
    void updateAccount() {
        UpdateAccountRequest request = new UpdateAccountRequest(UUID.randomUUID(),null, null,
                null, null, null,null,null);
        Account account = EntityCreator.getAccount();
        Account updatedAcc = EntityCreator.getUpgratedAccount();

        when(accountUtil.updateAccount(account, request)).thenReturn(updatedAcc);
        assertEquals(updatedAcc, accountUtil.updateAccount(account, request));

    }
}