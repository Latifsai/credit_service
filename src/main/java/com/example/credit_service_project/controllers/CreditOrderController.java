package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditOrderDTO.*;
import com.example.credit_service_project.fabrics.creditOrder.CreditOrderFabricImp;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class CreditOrderController {

    private final CreditOrderFabricImp fabric;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCreditOrderResponseDTO add(@RequestBody AddCreditOrderDTORequest request) {
        return fabric.add().execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditOrderResponseDTO> getAll() {
        return fabric.getAll().execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditOrderResponseDTO find(@PathVariable("id") @NotNull UUID id) {
        return fabric.search().execute(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditOrderResponseDTO update(@RequestBody UpdateCreditOrderDTORequest request) {
        return fabric.update().execute(request);
    }

    @PutMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreditOrderResponseDTO>  consideration() {
        return fabric.considerationOrderService().execute();
    }

}
