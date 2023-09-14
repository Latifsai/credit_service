package com.example.credit_service_project.services.credit;

import com.example.credit_service_project.dto.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.*;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repositories.CreditRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.agreement.AgreementCreateService;
import com.example.credit_service_project.services.agreement.SearchAgreementService;
import com.example.credit_service_project.services.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.services.generators.CreditDTOGenerator;
import com.example.credit_service_project.services.generators.DTOPaymentCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.paymentSchedule.PaymentScheduleGeneratorService;
import com.example.credit_service_project.services.utils.CreditUtil;
import com.example.credit_service_project.validation.exceptions.IsAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCreateServiceTest {
    @Mock
    private CreditRepository repository;
    @Mock
    private CreditUtil util;
    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private SearchAgreementService searchAgreementService;
    @Mock
    private CreditOrderSearchService searchCreditOrderService;
    @Mock
    private AccountUpdateService updateAccountService;
    @Mock
    private AgreementCreateService updateAgreementService;
    @Mock
    private PaymentScheduleGeneratorService paymentScheduleGeneratorService;
    @InjectMocks
    private CreditCreateService createService;

    @Test
    void createCredit() {
        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"),
                UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"), 12, 0, "Consumer credit");
        Account account = EntityCreator.getAccount();
        Agreement agreement = EntityCreator.getAgreement();
        CreditOrder creditOrder = EntityCreator.getCreditOrder();
        Credit credit = EntityCreator.getCredit();
        Product product = EntityCreator.getProduct();
        List<PaymentResponseDTO> list = List.of(DTOPaymentCreator.getPaymentResponseDTO());

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(repository.findByAccountAndCreditStatus(account, CreditStatus.ACTIVE)).thenReturn(Collections.emptyList());
        when(searchAgreementService.findById(request.getAgreementID())).thenReturn(agreement);
        when(searchCreditOrderService.findById(request.getCreditOrderID())).thenReturn(creditOrder);
        when(util.createCreditFromData(request, account, agreement, creditOrder)).thenReturn(credit);
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);
        when(updateAgreementService.saveAgreement(agreement)).thenReturn(agreement);
        when(repository.save(credit)).thenReturn(credit);
        when(paymentScheduleGeneratorService.generatePaymentSchedule(credit, product, account, agreement)).thenReturn(list);
        when(util.convertResponse(credit, list)).thenReturn(CreditDTOGenerator.getCreationResponse());

        CreateCreditDTOResponse result = createService.createCredit(request);

        assertNotNull(result);
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);
        verify(updateAgreementService, times(1)).saveAgreement(agreement);
        verify(repository, times(1)).save(credit);

    }

    @Test
    void createCreditIsAlreadyExistException() {
        CreateCreditRequestDTO request = new CreateCreditRequestDTO(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"),
                "A10B3U3OI9", UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"),
                UUID.fromString("3d542864-dbdb-431c-bf64-059898c4cfa9"), 12, 0, "Consumer credit");
        Account account = EntityCreator.getAccount();
        Credit credit = EntityCreator.getCredit();


        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(repository.findByAccountAndCreditStatus(account, CreditStatus.ACTIVE)).thenReturn(List.of(credit));

        assertThrows(IsAlreadyExistException.class, ()-> createService.createCredit(request));

    }

    @Test
    void saveCredit() {
        Credit credit = EntityCreator.getCredit();
        when(repository.save(credit)).thenReturn(credit);

        Credit result = createService.saveCredit(credit);
        verify(repository, times(1)).save(credit);
        assertEquals(credit, result);
    }
}