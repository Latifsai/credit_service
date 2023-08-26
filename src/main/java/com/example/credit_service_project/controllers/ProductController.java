package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.DTO.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.DTO.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.services.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductCreateService create;
    private final ProductDeleteService delete;
    private final GetAllProductsService get;
    private final ProductSearchService search;
    private final ProductUpdateService update;
    private final GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO add(@RequestBody AddProductDTORequest request) {
        return create.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDTO> getAllProducts() {
        return get.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponseDTO search(@PathVariable(name = "id") BigInteger id) {
        return search.searchProduct(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(@RequestBody UpdateProductDTORequest request) {
        return update.updateProduct(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponseDTO delete(@PathVariable BigInteger id) {
        return delete.deleteProduct(id);
    }

    @GetMapping("/preview")
    @ResponseStatus(HttpStatus.OK)
    public List<PreliminaryCalculationResponse> showPreliminary(@RequestParam(name = "accountID") UUID accountID,
                                                                @RequestParam(name = "accountNumber") String accountNumber,
                                                                @RequestParam(name = "productID") BigInteger productID,
                                                                @RequestParam(name = "monthTerm") Integer monthTerm,
                                                                @RequestParam(name = "interestRate") BigDecimal interestRate) {

        var request = new PreliminaryCalculationRequest(accountID, accountNumber, productID, monthTerm, interestRate);
        return getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request);
    }

}
