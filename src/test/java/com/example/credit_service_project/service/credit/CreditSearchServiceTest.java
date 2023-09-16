package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditSearchServiceTest {

    @Mock
    private CreditRepository repository;
    @InjectMocks
    private CreditSearchService creditSearchService;

    private final Account account = EntityCreator.getAccount();

    @Test
    @DisplayName("Test search credit by account and status method")
    void searchCreditByAccountAndStatus() {
        Credit credit = EntityCreator.getCredit();

        when(repository.findByAccountAndCreditStatus(account, CreditStatus.ACTIVE)).thenReturn(List.of(credit));

        Credit result = creditSearchService.searchCreditByAccountAndStatus(account, CreditStatus.ACTIVE);
        assertEquals(credit, result);
        verify(repository, times(1)).findByAccountAndCreditStatus(account, CreditStatus.ACTIVE);
    }

    @Test
    @DisplayName("Test search credit by account and status method throws NotFoundException")
    void searchCreditByAccountAndStatusNotFoundException() {
        when(repository.findByAccountAndCreditStatus(account, CreditStatus.ACTIVE)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> creditSearchService.searchCreditByAccountAndStatus(account, CreditStatus.ACTIVE));
        verify(repository, times(1)).findByAccountAndCreditStatus(account, CreditStatus.ACTIVE);
    }
}