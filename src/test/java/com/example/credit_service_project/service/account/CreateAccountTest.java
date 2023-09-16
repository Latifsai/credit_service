package com.example.credit_service_project.service.account;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.CreateAccountRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.generators.CreditHistoryDTOGenerator;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import com.example.credit_service_project.service.user.UserSearchService;
import com.example.credit_service_project.generators.AccountDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAccountTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private UserSearchService searchClientService;
    @Mock
    private AccountUtil util;
    @Mock
    private CreditHistoryService creditHistoryService;
    @InjectMocks
    private AccountCreationService service;

    @Test
    @DisplayName("Test test create account")
    public void testCreateAccount() {
        CreateAccountRequestDTO request = new CreateAccountRequestDTO(UUID.randomUUID(), "United States",
                BigDecimal.valueOf(3000), "USD", 10, 7);

        Account account = EntityCreator.getAccount();
        User client = EntityCreator.getUser();

        when(searchClientService.findUserById(request.getClientId())).thenReturn(client);
        when(util.convertAddRequestToAccount(request, client)).thenReturn(account);
        when(repository.save(account)).thenReturn(account);
        when(creditHistoryService.createHistory(account)).thenReturn(CreditHistoryDTOGenerator.getResponse());
        when(util.convertAccountToAddResponse(account)).thenReturn(AccountDTOGenerator.getResponse());


        AccountResponseDTO actual = service.createAccount(request);
        AccountResponseDTO expected = AccountDTOGenerator.getResponse();

        assertEquals(expected, actual);
        verify(searchClientService, times(1)).findUserById(request.getClientId());
        verify(util, times(1)).convertAddRequestToAccount(request, client);
        verify(repository, times(1)).save(account);
        verify(creditHistoryService, times(1)).createHistory(account);
        verify(util, times(1)).convertAccountToAddResponse(account);
    }

    @Test
    @DisplayName("Test when input contains null")
    public void testCreateAccountImpWithException() {
        Account account = EntityCreator.getAccountForValidation();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(account);

        assertEquals(4, violations.size());
    }

    @Test
    @DisplayName(value = "Test save account")
    public void saveAccount() {
        Account account = EntityCreator.getAccount();

        when(repository.save(account)).thenReturn(account);

        assertEquals(account, repository.save(account));
        verify(repository, times(1)).save(account);
    }

}
