package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.utils.generator.EURToAnyGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ProductUtil {

    private final Map<String, Double> currencyMap = EURToAnyGenerator.generatCurrencyMap();

    private final Integer maxAmountCriterionForDeposit = 25_000;
    private final Integer minAmountCriterionForDeposit = 10_000;

    public Product convertFromAddRequestToResponse(CreateProductDTORequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setSum(request.getSum());
        product.setDetails(request.getDetails());
        product.setCalculationType(request.getCalculationType());
        product.setCurrencyCode(request.getCurrencyCode());
        product.setNeedGuaranty(needGuaranty(request.getSum(), request.getCurrencyCode()));
        product.setEarlyRepayment(earlyRepayment(request.getSum(), request.getCurrencyCode()));
        product.setNeedIncomeDetails(true);
        return product;
    }

    private boolean earlyRepayment(BigDecimal sum, String currencyCode) {
        return currencyMap.containsKey(currencyCode)
                && sum.intValue() <= (minAmountCriterionForDeposit * currencyMap.get(currencyCode));
    }

    private boolean needGuaranty(BigDecimal sum, String currencyCode) {
        return currencyMap.containsKey(currencyCode)
                && sum.intValue() >= (maxAmountCriterionForDeposit * currencyMap.get(currencyCode));
    }

    public ProductResponseDTO toResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sum(product.getSum())
                .needGuaranty(product.isNeedGuaranty())
                .earlyRepayment(product.isEarlyRepayment())
                .needIncomeDetails(product.isNeedIncomeDetails())
                .details(product.getDetails())
                .currencyCode(product.getCurrencyCode())
                .calculationType(product.getCalculationType())
                .build();
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
