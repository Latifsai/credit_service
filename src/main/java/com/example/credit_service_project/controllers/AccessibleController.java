package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.services.product.GetAllProductsService;
import com.example.credit_service_project.services.product.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * This request can be used by all Users without authorisation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/all")
public class AccessibleController {
    private final GetAllProductsService get;
    private final ProductSearchService search;
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDTO> getAllProducts() {
        return get.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponseDTO searchProduct(@PathVariable(name = "id") Long id) {
        return search.searchProduct(id);
    }

}
