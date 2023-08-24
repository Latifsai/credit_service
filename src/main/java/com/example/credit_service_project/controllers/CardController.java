package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.services.card.CreateCardServiceImp;
import com.example.credit_service_project.services.card.GetCardsServiceImp;
import com.example.credit_service_project.services.card.SearchCardServiceImp;
import com.example.credit_service_project.services.card.UpdateCardServiceImp;
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

    private final CreateCardServiceImp create;
    private final GetCardsServiceImp get;
    private final SearchCardServiceImp search;
    private final UpdateCardServiceImp update;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse createCard(@RequestBody AddCardDTORequest request) {
        return create.execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CardDTOResponse> getCards() {
        return get.execute();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTOResponse updateCard(@RequestBody UpdateCardDTORequest request) {
        return update.execute(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CardDTOResponse searchCard(@PathVariable("id") @NotNull UUID id) {
        return search.execute(id);
    }

}
