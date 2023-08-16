package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.fabrics.card.CardFabric;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardFabric fabric;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse createCard(@RequestBody AddCardDTORequest request) {
        return fabric.activateCreateCard().execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CardDTOResponse> getCards() {
        return fabric.getCardsService().execute();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse updateCard(@RequestBody UpdateCardDTORequest request) {
        return fabric.activateUpdateCard().execute(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CardDTOResponse searchCard(@PathVariable("id") @NotNull UUID id) {
        return fabric.activateSearchCard().execute(id);
    }

}
