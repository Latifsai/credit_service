package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.service.utils.AccountUtil;
import com.example.credit_service_project.serviceTest.generators.DTOAccountCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateServiceImpTest {
    @Mock
    private AccountRepository repository;

    @Mock
    private AccountUtil util;

    @InjectMocks
    private UpdateAccountServiceImp service;

    @Test
    public void upgradeTestSuccess() {
        var request = DTOAccountCreator.getUpdateRequest();
        var account = EntityCreator.getAccount();
        var updatedAccount = EntityCreator.getUpgratedAccount();
        when(repository.findByIdOrAccountNumber(request.getAccountID(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));

        when(util.updateAccount(account, request)).thenReturn(EntityCreator.getUpgratedAccount());
        when(util.convertAccountToAddResponse(updatedAccount)).thenReturn(DTOAccountCreator.getUpdatedDTOResponse());
        assertEquals(DTOAccountCreator.getUpdatedDTOResponse(), service.execute(request));
    }

    @Test
    public void upgradeTestNotFound() {
        var request = DTOAccountCreator.getUpdateRequest();
        when(repository.findByIdOrAccountNumber(request.getAccountID(), request.getAccountNumber()))
                .thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> service.execute(request));
    }

    @Test
    public void TestUpdateValidation() {
        var request = DTOAccountCreator.getUpdateRequestWithErrors();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(2, set.size());
    }
}
