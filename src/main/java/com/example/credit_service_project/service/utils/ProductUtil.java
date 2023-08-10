package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUtil {

    private final List<String> currency = new ArrayList<>();

    {
        currency.add("$");
        currency.add("€");
        currency.add("£");
        currency.add("¥");
        currency.add("₽");
        currency.add("₪");
        currency.add("₴");
    }

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

    private boolean needGuaranty(BigDecimal sum, String currencyCode) {
        if (currencyCode.equals(currency.get(0)) && sum.intValue() >= 30_000) return true;
        if (currencyCode.equals(currency.get(1)) && sum.intValue() >= 35_000) return true;
        if (currencyCode.equals(currency.get(2)) && sum.intValue() >= 26_086) return true;
        if (currencyCode.equals(currency.get(3)) && sum.intValue() >= 245_000) return true;
        if (currencyCode.equals(currency.get(4)) && sum.intValue() >= 3_715_250 ) return true;
        if (currencyCode.equals(currency.get(5)) && sum.intValue() >= 143_150) return true;
        if (currencyCode.equals(currency.get(6)) && sum.intValue() >= 1_412_250) return true;

        return false;
    }

    private boolean earlyRepayment(BigDecimal sum, String currencyCode) {
        if (currencyCode.equals(currency.get(0)) && sum.intValue() <= 12_000) return true;
        if (currencyCode.equals(currency.get(1)) && sum.intValue() <= 14_700) return true;
        if (currencyCode.equals(currency.get(2)) && sum.intValue() <= 12_201) return true;
        if (currencyCode.equals(currency.get(3)) && sum.intValue() <= 116571) return true;
        if (currencyCode.equals(currency.get(4)) && sum.intValue() <= 1_572_312) return true;
        if (currencyCode.equals(currency.get(5)) && sum.intValue() <= 72_471) return true;
        if (currencyCode.equals(currency.get(6)) && sum.intValue() <= 594_615) return true;

        return false;
    }


}
