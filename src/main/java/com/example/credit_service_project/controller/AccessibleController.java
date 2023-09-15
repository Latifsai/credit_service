package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.service.product.GetAllProductsService;
import com.example.credit_service_project.service.product.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This request can be used by all Users without authorisation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/all/products")
public class AccessibleController {
    private final GetAllProductsService get;
    private final ProductSearchService search;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<ProductResponseDTO> getAllProducts() {
        return get.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponseDTO searchProduct(@PathVariable(name = "id") Long id) {
        return search.searchProduct(id);
    }

}
