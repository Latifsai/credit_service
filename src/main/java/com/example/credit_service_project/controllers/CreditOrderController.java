package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.services.creditOrder.CreditOrderCreateService;
import com.example.credit_service_project.services.creditOrder.CreditOrderUpdateService;
import com.example.credit_service_project.services.creditOrder.DecisionOrderService;
import com.example.credit_service_project.services.creditOrder.GetAllCreditOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class CreditOrderController {

    private final CreditOrderCreateService create;
    private final DecisionOrderService decisionOrderService;
    private final GetAllCreditOrdersService getAllOrders;
    private final CreditOrderUpdateService update;

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

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditOrderResponseDTO update(@RequestBody UpdateCreditOrderRequestDTO request) {
        return update.updateCreditOrder(request);
    }

    @PutMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreditOrderResponseDTO> decision() {
        return decisionOrderService.acceptOrder();
    }

}
