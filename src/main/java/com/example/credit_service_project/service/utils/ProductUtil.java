package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generator.ProductGenerator;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.CurrencyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductUtil {

    private final Map<String, Double> currencyMap = ProductGenerator.generatCurrencyMap();

    private final Integer maxAmountCriterionForDeposit = 18_000;
    private final Integer minAmountCriterionForDeposit = 7_500;

    public Product convertFromAddRequestToResponse(AddProductDTORequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setSum(request.getSum());
        product.setDetails(request.getDetails());
        product.setCalculationType(request.getCalculationType());
        product.setCurrencyCode(request.getCurrencyCode());
        product.setNeedGuaranty(needGuaranty(product.getSum(), product.getCurrencyCode()));
        product.setEarlyRepayment(earlyRepayment(product.getSum(), product.getCurrencyCode()));
        product.setNeedIncomeDetails(true);
        return product;
    }

    private boolean earlyRepayment(BigDecimal sum, String currencyCode) {
        if (currencyCode.equals("EUR") && sum.intValue() <= minAmountCriterionForDeposit) return true;

        for (String code : currencyMap.keySet()) {
            if (currencyCode.equals(code) && sum.intValue() <= (maxAmountCriterionForDeposit * currencyMap.get(currencyCode))) {
                return true;
            }
        }

        throw new CurrencyException(ErrorsMessage.UNABLE_INACCESSIBLE_CURRENCY);
    }

    private boolean needGuaranty(BigDecimal sum, String currencyCode) {
        if (currencyCode.equals("EUR") && sum.intValue() >= maxAmountCriterionForDeposit) return true;

        for (String code : currencyMap.keySet()) {
            if (currencyCode.equals(code) && sum.intValue() >= (maxAmountCriterionForDeposit * currencyMap.get(currencyCode))) {
                return true;
            }
        }
        throw new CurrencyException(ErrorsMessage.UNABLE_INACCESSIBLE_CURRENCY);
    }

    public ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getSum(),
                product.isNeedGuaranty(),
                product.isEarlyRepayment(),
                product.isNeedIncomeDetails(),
                product.getDetails(),
                product.getCurrencyCode(),
                product.getCalculationType()
        );
    }

    public Product update(Product product, UpdateProductDTORequest request) {
        if (request.getSum() != null) product.setSum(request.getSum());
        if (request.getNeedGuaranty() != null) product.setNeedGuaranty(request.getNeedGuaranty());
        if (request.getEarlyRepayment() != null) product.setEarlyRepayment(request.getEarlyRepayment());
        if (request.getNeedIncomeDetails() != null) product.setNeedIncomeDetails(request.getNeedIncomeDetails());
        if (request.getDetails() != null && !request.getDetails().trim().isEmpty()) {
            product.setDetails(request.getDetails());
        }
        if (request.getCalculationType() != null && !request.getCalculationType().name().isEmpty()) {
            product.setCalculationType(request.getCalculationType());
        }
        return product;
    }


}
