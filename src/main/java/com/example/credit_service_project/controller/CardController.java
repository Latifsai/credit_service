package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.service.card.CardCreateService;
import com.example.credit_service_project.service.card.CardUpdateService;
import com.example.credit_service_project.service.card.GetAllCardsService;
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
    @ResponseStatus(HttpStatus.OK)
    public CardResponseDTO updateCard(@RequestBody UpdateCardRequest request) {
        return update.saveUpdateCard(request);
    }

}
