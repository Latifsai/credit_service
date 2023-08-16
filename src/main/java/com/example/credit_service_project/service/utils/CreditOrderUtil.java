package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.UpdateCreditOrderDTORequest;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.service.generator.CreditOrderGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreditOrderUtil {

    public CreditOrder convertAddRequestToEntity(AddCreditOrderDTORequest request, Product product) {
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setProduct(product);
        creditOrder.setNumber(CreditOrderGenerator.createCreditOrderNumber(request.getNumberLength()));
        creditOrder.setAmount(product.getSum());
        creditOrder.setCreationDate(LocalDate.now());
        creditOrder.setClientIncome(request.getClientIncome());
        creditOrder.setClientMonthlyExpenditure(request.getClientMonthlyExpenditure());
        creditOrder.setMaxPeriodMonths(CreditOrderGenerator.maxPeriod);
        creditOrder.setMinPeriodMonths(CreditOrderGenerator.minPeriod);
        creditOrder.setCreditOrderStatus(CreditOrderStatus.ACCEPTED);
        return creditOrder;
    }

    public AddCreditOrderResponseDTO convertToAddResponse(CreditOrder order, Product product) {
        return new AddCreditOrderResponseDTO(
                product.getId(),
                product.getName(),
                order.getId(),
                order.getNumber(),
                order.getAmount(),
                order.getCreationDate(),
                order.getMaxPeriodMonths(),
                order.getMinPeriodMonths(),
                order.getCreditOrderStatus()
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
        if (request.getCreditOrderStatus() != null) creditOrder.setCreditOrderStatus(request.getCreditOrderStatus());
        if (request.getClientIncome() != null) creditOrder.setClientIncome(request.getClientIncome());
        if (request.getClientMonthlyExpenditure() != null) creditOrder.setClientMonthlyExpenditure(request.getClientMonthlyExpenditure());

        return creditOrder;
    }
}
