package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.dto.delayDTO.DelayResponse;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import com.example.credit_service_project.service.delay.DelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("credit_history")
@RequiredArgsConstructor
public class CreditHistoryController {

    private final CreditHistoryService creditHistoryService;
    private final DelayService delayService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CreditHistoryResponse> findAll() {
        return creditHistoryService.findAllHistories();
    }

    @GetMapping("/delays/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<DelayResponse> findAllDelaysBelongsCreditHistory(@PathVariable(name = "id") UUID historyId) {
        return delayService.findAllDelaysBelongsToCreditHistory(historyId);
    }
}
