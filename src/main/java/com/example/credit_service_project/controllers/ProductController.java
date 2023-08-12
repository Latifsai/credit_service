package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.ProductDTO.AddProductDTORequest;
import com.example.credit_service_project.DTO.ProductDTO.ProductResponseDTO;
import com.example.credit_service_project.DTO.ProductDTO.UpdateProductDTORequest;
import com.example.credit_service_project.fabrics.product.ProductFabricImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductFabricImp fabric;

  @PostMapping
  public ResponseEntity<ProductResponseDTO> add (@RequestBody AddProductDTORequest request) {
      var response = fabric.create().execute(request);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts () {
        var response = fabric.getList().execute();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> search (@PathVariable BigInteger id) {
        var response = fabric.search().execute(id);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDTO> update (@RequestBody UpdateProductDTORequest request) {
        var response = fabric.update().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> delete (@PathVariable BigInteger id) {
        var response = fabric.delete().execute(id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }



}
