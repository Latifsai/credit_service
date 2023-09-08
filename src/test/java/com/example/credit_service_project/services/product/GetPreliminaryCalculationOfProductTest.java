package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.DTO.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPreliminaryCalculationOfProductTest {

    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private ProductSearchService searchService;
    @Mock
    private PaymentScheduleUtil util;
    @InjectMocks
    private GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;

    @Test
    void getPreliminaryCalculation() {
        PreliminaryCalculationRequest request = new PreliminaryCalculationRequest(UUID.randomUUID(), null,
                1L, 12, BigDecimal.TEN);

        Account account = EntityCreator.getAccount();
        Product product = EntityCreator.getProduct();
        BigDecimal[] payments = new BigDecimal[12];

        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(searchService.findById(request.getProductID())).thenReturn(product);
        when(util.calculatePayment(request.getMonthTerm(), request.getInterestRate(), product.getSum(), product)).thenReturn(payments);

        List<PreliminaryCalculationResponse> actual = getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request);

        assertNotNull(actual);
        assertEquals(12, actual.size());
    }
}