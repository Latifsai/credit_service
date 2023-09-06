package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.DTO.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.services.product.GetAllProductsService;
import com.example.credit_service_project.services.product.GetPreliminaryCalculationOfProduct;
import com.example.credit_service_project.services.product.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all")
public class AccessibleController {
    private final GetAllProductsService get;
    private final ProductSearchService search;
    private final GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.FOUND) //permit all
    public List<ProductResponseDTO> getAllProducts() {
        return get.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.FOUND) //permit all
    public ProductResponseDTO search(@PathVariable(name = "id") BigInteger id) {
        return search.searchProduct(id);
    }

    @GetMapping("/credit_preview")
    @ResponseStatus(HttpStatus.OK) // permit all
    public List<PreliminaryCalculationResponse> showPreliminary(@RequestParam(name = "accountID") UUID accountID,
                                                                @RequestParam(name = "accountNumber") String accountNumber,
                                                                @RequestParam(name = "productID") BigInteger productID,
                                                                @RequestParam(name = "monthTerm") Integer monthTerm,
                                                                @RequestParam(name = "interestRate") BigDecimal interestRate) {

        var request = new PreliminaryCalculationRequest(accountID, accountNumber, productID, monthTerm, interestRate);
        return getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request);
    }


}
