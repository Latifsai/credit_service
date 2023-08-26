package com.example.credit_service_project.services.product;

import com.example.credit_service_project.DTO.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.DTO.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.utils.PaymentScheduleUtil;
import com.example.credit_service_project.services.utils.calculators.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPreliminaryCalculationOfProduct {
    private final AccountSearchService accountSearchService;
    private final ProductSearchService searchService;
    private final PaymentScheduleUtil util;

    public List<PreliminaryCalculationResponse> getPreliminaryCalculation(PreliminaryCalculationRequest request) {
        List<PreliminaryCalculationResponse> responses = new ArrayList<>();
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Product product = searchService.findById(request.getProductID());

        BigDecimal transferredSum = CurrencyConverter.convertCurrency(product, account);

        BigDecimal[] payments = util.calculatePayment(request.getMonthTerm(),  request.getInterestRate(),
                transferredSum, product);

        for (int month = 0; month < request.getMonthTerm(); month++) {
            int actualMonth = month + 1;
            responses.add(new PreliminaryCalculationResponse((actualMonth), LocalDate.now().plusMonths(actualMonth), payments[month]));
        }

        return responses;
    }

}
