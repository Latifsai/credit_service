package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.operation.GetOperationsService;
import com.example.credit_service_project.services.operation.OperationSearchService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final CardSearchService cardSearch;
    private final AccountSearchService accountSearch;
    private final OperationSearchService operationSearch;
    private final GetOperationsService getAllOperations;
    @GetMapping("/account/search") // может юзер и админ
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDTOResponse searchAccount(@RequestBody SearchAccountRequest request) {
        return accountSearch.searchAccount(request);
    }

    @GetMapping("/card/{id}") // может юзер и админ
    @ResponseStatus(HttpStatus.FOUND)
    public CardDTOResponse searchCard(@PathVariable("id") @NotNull UUID id) {
        return cardSearch.searchCard(id);
    }

    @GetMapping("/operations/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OperationResponseDTO> get(@RequestBody GetBelongsAccountOperationsRequest request) {
        return getAllOperations.getAllOperations(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND) // админ и юзер
    public OperationResponseDTO search(@PathVariable("id") @NotNull UUID id) {
        return operationSearch.searchOperation(id);
    }


}
