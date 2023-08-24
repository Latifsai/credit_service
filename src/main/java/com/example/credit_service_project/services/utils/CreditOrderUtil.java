package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.UpdateCreditOrderDTORequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.generator.CreditOrderGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static com.example.credit_service_project.entity.enums.CreditOrderStatus.*;

@Service
public class CreditOrderUtil {

    public CreditOrder convertAddRequestToEntity(AddCreditOrderDTORequest request, Product product, Client client) {
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setProduct(product);
        creditOrder.setNumber(CreditOrderGenerator.createCreditOrderNumber(request.getNumberLength()));
        creditOrder.setAmount(CreditOrderGenerator.convertCurrency(product, client.getAccount()));
        creditOrder.setCreationDate(LocalDate.now());
        creditOrder.setLastUpdateDate(creditOrder.getCreationDate());
        creditOrder.setClientSalary(client.getSalary());
        creditOrder.setPassiveIncome(client.getPassiveIncome());
        creditOrder.setClientMonthlyExpenditure(client.getExpenses());
        creditOrder.setMaxPeriodMonths(CreditOrderGenerator.maxPeriod);
        creditOrder.setMinPeriodMonths(CreditOrderGenerator.minPeriod);
        creditOrder.setCreditOrderStatus(IN_REVIEW);
        return creditOrder;
    }

    public AddCreditOrderResponseDTO convertToAddResponse(CreditOrder order, Product product, Client client) {
        return new AddCreditOrderResponseDTO(
                product.getId(),
                product.getName(),
                order.getId(),
                order.getNumber(),
                order.getAmount(),
                order.getCreationDate(),
                order.getMaxPeriodMonths(),
                order.getMinPeriodMonths(),
                order.getCreditOrderStatus(),
                client.getAccount().getCurrency()
        );
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

    public CreditOrder update(UpdateCreditOrderDTORequest request, CreditOrder creditOrder) {

        if (request.getAmount() != null) creditOrder.setAmount(request.getAmount());
        creditOrder.setCreditOrderStatus(request.getCreditOrderStatus());
        if (request.getClientSalary() != null) creditOrder.setClientSalary(request.getClientSalary());
        if (request.getPassiveIncome() != null) creditOrder.setPassiveIncome(request.getPassiveIncome());
        if (request.getClientMonthlyExpenditure() != null) {
            creditOrder.setClientMonthlyExpenditure(request.getClientMonthlyExpenditure());
        }
        creditOrder.setLastUpdateDate(LocalDate.now());
            return creditOrder;

    }

    public CreditOrder considerationOfApplication(CreditOrder order) {
        if (getMountPayment(order).compareTo(get15PercentFromIncome(order)) >= 0
            || order.getPassiveIncome().compareTo(new BigDecimal("500")) >= 0) {
            order.setCreditOrderStatus(APPROVED);
        } else {
            order.setCreditOrderStatus(DECLINED);
        }
        return order;
    }

    private BigDecimal get15PercentFromIncome(CreditOrder order) {
        return ((order.getClientSalary().add(order.getPassiveIncome())).multiply(new BigDecimal("15")))
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getMountPayment(CreditOrder order) {
        return order.getAmount().divide(new BigDecimal(order.getMinPeriodMonths()), 2, RoundingMode.HALF_EVEN);
    }
}
