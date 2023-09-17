package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.dto.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AccountUtilTest {
    @InjectMocks
    private AccountUtil accountUtil;
    private final Account account = EntityCreator.getAccount();

    @Test
    @DisplayName("Test convertAddRequestToAccount method")
    void convertAddRequestToAccount() {
        CreateAccountRequestDTO request = new CreateAccountRequestDTO(UUID.randomUUID(),
                "United States", BigDecimal.valueOf(3000), "USD", 10, 4);
        User user = EntityCreator.getUser();

        Account result = accountUtil.convertAddRequestToAccount(request, user);
        assertNotNull(result);

    }

    @Test
    @DisplayName("Test convertAccountToAddResponse method")
    void convertAccountToAddResponse() {
        AccountResponseDTO result = accountUtil.convertAccountToAddResponse(account);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test updateAccount method")
    void updateAccount() {
        UpdateAccountRequest request = new UpdateAccountRequest(UUID.randomUUID(),null, null,
                null, null, null,null,null);
        assertNotNull(accountUtil.updateAccount(account, request));
    }
}