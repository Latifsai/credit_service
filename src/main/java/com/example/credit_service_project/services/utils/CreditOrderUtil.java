package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.calculators.CurrencyConverter;
import com.example.credit_service_project.services.utils.generator.FieldsGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.credit_service_project.entity.enums.CreditOrderStatus.*;
import static java.math.RoundingMode.HALF_EVEN;

@Service
public class CreditOrderUtil {
    private final Integer minPeriod = 12;
    private final Integer maxPeriod = 120;

    public CreditOrder convertAddRequestToEntity(CreateCreditOrderRequestDTO request, Product product, Account account) {
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setProduct(product);
        creditOrder.setNumber(FieldsGenerator.generateRandomNumber(request.getNumberLength()));
        creditOrder.setAmount(CurrencyConverter.convertCurrency(product, account));
        creditOrder.setCreationDate(LocalDate.now());
        creditOrder.setLastUpdateDate(creditOrder.getCreationDate());
        creditOrder.setClientSalary(account.getUser().getSalary());
        creditOrder.setPassiveIncome(account.getUser().getPassiveIncome());
        creditOrder.setClientMonthlyExpenditure(account.getUser().getExpenses());
        creditOrder.setMaxPeriodMonths(maxPeriod);
        creditOrder.setMinPeriodMonths(minPeriod);
        creditOrder.setCreditOrderStatus(IN_REVIEW);
        creditOrder.setClientEmail(account.getUser().getEmail());
        return creditOrder;
    }

    public CreateCreditOrderResponseDTO convertToAddResponse(CreditOrder order, Product product, Account account) {
        return CreateCreditOrderResponseDTO.builder()
                .productID(product.getId())
                .productName(product.getName())
                .id(order.getId())
                .number(order.getNumber())
                .amount(order.getAmount())
                .creationDate(order.getCreationDate())
                .maxPeriodMonths(order.getMaxPeriodMonths())
                .minPeriodMonths(order.getMinPeriodMonths())
                .creditOrderStatus(order.getCreditOrderStatus())
                .currency(account.getCurrency())
                .build();
    }

    public CreditOrderResponseDTO convertToResponse(CreditOrder order) {
        return new CreditOrderResponseDTO(
                order.getId(),
                order.getNumber(),
                order.getAmount(),
                order.getCreationDate(),
                order.getMaxPeriodMonths(),
                order.getMinPeriodMonths(),
                order.getCreditOrderStatus()
        );
    }

    public CreditOrder update(UpdateCreditOrderRequestDTO request, CreditOrder creditOrder) {

        if (request.getStatus() != null) creditOrder.setCreditOrderStatus(request.getStatus());
        if (request.getClientSalary() != null) creditOrder.setClientSalary(request.getClientSalary());
        if (request.getPassiveIncome() != null) creditOrder.setPassiveIncome(request.getPassiveIncome());
        if (request.getClientMonthlyExpenditure() != null) {
            creditOrder.setClientMonthlyExpenditure(request.getClientMonthlyExpenditure());
        }
        creditOrder.setLastUpdateDate(LocalDate.now());
        return creditOrder;

    }

    public CreditOrder considerationOfApplication(CreditOrder order) {
        if (getMountPayment(order).compareTo(get30PercentFromIncome(order)) >= 0
                || order.getPassiveIncome().compareTo(new BigDecimal("1000")) >= 0) {
            order.setCreditOrderStatus(APPROVED);
        } else {
            order.setCreditOrderStatus(DECLINED);
        }
        order.setLastUpdateDate(LocalDate.now());
        return order;
    }

    private BigDecimal get30PercentFromIncome(CreditOrder order) {
        return ((order.getClientSalary().subtract(order.getClientMonthlyExpenditure()).add(order.getPassiveIncome()))
                .multiply(new BigDecimal("30")))
                .divide(new BigDecimal("100"), 2, HALF_EVEN);
    }

    private BigDecimal getMountPayment(CreditOrder order) {
        return order.getAmount().divide(
                (BigDecimal.valueOf(order.getMaxPeriodMonths()).divide(BigDecimal.valueOf(2), 0, HALF_EVEN)),
                2, HALF_EVEN);
    }
}