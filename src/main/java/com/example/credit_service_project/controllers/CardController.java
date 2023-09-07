package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.DTO.cardDTO.CardResponseDTO;
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
    public CardResponseDTO createCard(@RequestBody CreateCardRequestDTO request) {
        return create.createCard(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CardResponseDTO> getCards() {
        return get.getAllCards();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponseDTO updateCard(@RequestBody UpdateCardDTORequest request) {
        return update.updateCard(request);
    }

}
