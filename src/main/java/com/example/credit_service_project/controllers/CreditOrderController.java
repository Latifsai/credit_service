package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.UpdateCreditOrderDTORequest;
import com.example.credit_service_project.services.creditOrder.*;
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

    private final CreateCreditOrderServiceImp create;
    private final DecisionOrderService considerationOrderService;
    private final GetAllCreditOrdersService getAllOrders;
    private final SearchCreditOrderServiceImp search;
    private final UpdateCreditOrderServiceImp update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddCreditOrderResponseDTO add(@RequestBody AddCreditOrderDTORequest request) {
        return create.execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditOrderResponseDTO> getAll() {
        return getAllOrders.execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditOrderResponseDTO find(@PathVariable("id") @NotNull UUID id) {
        return search.execute(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditOrderResponseDTO update(@RequestBody UpdateCreditOrderDTORequest request) {
        return update.execute(request);
    }

    @PutMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreditOrderResponseDTO>  consideration() {
        return considerationOrderService.execute();
    }

}
