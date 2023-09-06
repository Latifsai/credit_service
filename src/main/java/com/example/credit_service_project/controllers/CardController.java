package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.services.card.CardCreateService;
import com.example.credit_service_project.services.card.CardUpdateService;
import com.example.credit_service_project.services.card.GetAllCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardCreateService create;
    private final GetAllCardsService get;
    private final CardUpdateService update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse createCard(@RequestBody AddCardDTORequest request) {
        return create.createService(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CardDTOResponse> getCards() {
        return get.getAllCards();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse updateCard(@RequestBody UpdateCardDTORequest request) {
        return update.updateCard(request);
    }

}
