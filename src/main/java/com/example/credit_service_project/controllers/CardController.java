package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.fabrics.card.CardFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/card")
@RequiredArgsConstructor
public class CardController {
    private final CardFabric fabric;

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody AddCardDTORequest request) {
        var response = fabric.activateCreateCard().execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCard(@RequestBody DeleteCardDTORequest request) {
        var response = fabric.activateDeleteCard().execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCard(@RequestBody GetCardsRequest request) {
        var response = fabric.activateGetCard().execute(request);
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
