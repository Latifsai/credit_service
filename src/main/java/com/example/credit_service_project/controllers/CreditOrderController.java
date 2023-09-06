package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditOrderDTO.*;
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

    private final CreditOrderCreateService create;
    private final DecisionOrderService decisionOrderService;
    private final GetAllCreditOrdersService getAllOrders;
    private final CreditOrderSearchService search;
    private final CreditOrderUpdateService update;
    private final CheckCreditOrderStatusService checkCreditOrderStatus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCreditOrderResponseDTO create(@RequestBody CreateCreditOrderDTORequest request) {
        return create.createCreditOrder(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditOrderResponseDTO> getAll() {
        return getAllOrders.getAllCreditOrders();
    }

    @GetMapping("/{id}") //админ и юзер
    @ResponseStatus(HttpStatus.FOUND)
    public CreditOrderResponseDTO find(@PathVariable("id") @NotNull UUID id) {
        return search.searchCreditOrder(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditOrderResponseDTO update(@RequestBody UpdateCreditOrderDTORequest request) {
        return update.updateCreditOrder(request);
    }

    @PutMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreditOrderResponseDTO> decision() {
        return decisionOrderService.acceptOrder();
    }

    @GetMapping("/check/{id}")// админ и юзер
    @ResponseStatus(HttpStatus.FOUND)
    public CheckCreditOrderStatusResponse checkStatus(@PathVariable("id") @NotNull UUID id) {
        return checkCreditOrderStatus.checkOrderStatus(id);
    }


}
