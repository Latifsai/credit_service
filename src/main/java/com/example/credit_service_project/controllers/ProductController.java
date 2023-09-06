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
    private final ProductUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO add(@RequestBody AddProductDTORequest request) {
        return create.createProduct(request);
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

}
