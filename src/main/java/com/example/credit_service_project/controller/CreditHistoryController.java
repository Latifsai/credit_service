package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.creditHistoryDTO.CreditHistoryResponse;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("credit_history")
@RequiredArgsConstructor
public class CreditHistoryController {

    private final CreditHistoryService creditHistoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditHistoryResponse> findAll() {
        return creditHistoryService.findAllHistories();
    }

}
