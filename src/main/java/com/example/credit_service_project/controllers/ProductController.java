package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.services.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductServiceImp create;
    private final DeleteProductServiceImp delete;
    private final GetProductsListService get;
    private final SearchProductServiceImp search;
    private final UpdateProductServiceImp update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO add(@RequestBody AddProductDTORequest request) {
        return create.execute(request);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDTO> getAllProducts() {
        return get.execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponseDTO search(@PathVariable BigInteger id) {
        return search.execute(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(@RequestBody UpdateProductDTORequest request) {
        return update.execute(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponseDTO delete(@PathVariable BigInteger id) {
        return delete.execute(id);
    }


}
