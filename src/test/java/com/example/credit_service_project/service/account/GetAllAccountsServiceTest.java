package com.example.credit_service_project.service.account;

import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.generators.AccountDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.AccountUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllAccountsServiceTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountUtil util;

    @InjectMocks
    private GetAllAccountsService service;

    @Test
    @DisplayName(value = "Get all Accounts test")
    public void getAccountsListTest() {
        var accounts = List.of(EntityCreator.getAccount());
        var responses = List.of(AccountDTOGenerator.getResponse());

        when(repository.findAll()).thenReturn(accounts);
        when(util.convertAccountToAddResponse(accounts.get(0))).thenReturn(responses.get(0));

        assertEquals(responses, service.getAllAccounts());
    }

    @Test
    @DisplayName(value = "Get all Accounts test if empty")
    public void testAccountListEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), service.getAllAccounts());
    }
}
