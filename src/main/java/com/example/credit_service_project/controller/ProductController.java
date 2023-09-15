package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.ProductDTO.CreateProductDTORequest;
import com.example.credit_service_project.dto.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.dto.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.service.product.ProductCreateService;
import com.example.credit_service_project.service.product.ProductDeleteService;
import com.example.credit_service_project.service.product.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductCreateService create;
    private final ProductDeleteService delete;
    private final ProductUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO add(@RequestBody CreateProductDTORequest request) {
        return create.createProduct(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(@RequestBody UpdateProductDTORequest request) {
        return update.updateProduct(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductResponseDTO delete(@PathVariable Long id) {
        return delete.deleteProduct(id);
    }

}
