package com.example.credit_service_project.controllers;

import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.services.product.GetAllProductsService;
import com.example.credit_service_project.services.product.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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
    public ResponseEntity<ProductResponseDTO> searchProduct(@PathVariable(name = "id") Long id) {
        ProductResponseDTO result = search.searchProduct(id);
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(result);
    }

}
