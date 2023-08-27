package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.account.GetAllAccountsService;
import com.example.credit_service_project.services.utils.AccountUtil;
import com.example.credit_service_project.serviceTest.generators.DTOAccountCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
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
    public void getAccountsListTest() {
        var accounts = List.of(EntityCreator.getAccount());
        var responses = List.of(DTOAccountCreator.createDTOResponse());

        when(repository.findAll()).thenReturn(accounts);

        when(util.convertAccountToAddResponse(accounts.get(0))).thenReturn(responses.get(0));

        assertEquals(responses, service.getAllAccounts());
    }
    @Test
    public void testAccountListEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(), service.getAllAccounts());
    }
}
