package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.SearchCardDTOCreditRequest;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.fabrics.card.CardFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardFabric fabric;

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody AddCardDTORequest request) {
        var response = fabric.activateCreateCard().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCard() {
        var response = fabric.getCardsService().execute();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody UpdateCardDTORequest request) {
        var response = fabric.activateUpdateCard().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCard(@RequestBody SearchCardDTOCreditRequest request) {
        var response = fabric.activateSearchCard().execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
