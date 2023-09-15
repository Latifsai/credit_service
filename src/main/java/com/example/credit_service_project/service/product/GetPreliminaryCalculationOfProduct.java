package com.example.credit_service_project.service.product;

import com.example.credit_service_project.dto.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.dto.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.utils.PaymentScheduleUtil;
import com.example.credit_service_project.service.utils.calculators.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPreliminaryCalculationOfProduct {
    private final AccountSearchService accountSearchService;
    private final ProductSearchService searchService;
    private final PaymentScheduleUtil util;

    public List<PreliminaryCalculationResponse> getPreliminaryCalculation(PreliminaryCalculationRequest request) {
        List<PreliminaryCalculationResponse> responses = new ArrayList<>();
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Product product = searchService.findById(request.getProductID());

        BigDecimal transferredSum = CurrencyConverter.convertCurrency(product, account);

        BigDecimal[] payments = util.calculatePayment(request.getMonthTerm(), request.getInterestRate(),
                transferredSum, product);

        for (int month = 0; month < request.getMonthTerm(); month++) {
            int actualMonth = month + 1;
            responses.add(new PreliminaryCalculationResponse((actualMonth),
                    LocalDate.now().plusMonths(actualMonth), payments[month], account.getCurrency()));
        }
        log.info("Get preliminary calculation for a Product with ID: {}", product.getId());
        return responses;
    }

}
