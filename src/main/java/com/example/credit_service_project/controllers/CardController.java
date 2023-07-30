package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService<AddedAndSearchCardDTOResponse, AddCardDTORequest> createCard;
    private final CardService<DeleteCardDTOResponse, DeleteCardDTORequest> deleteCard;
    private final CardService<GetCardsResponse, GetCardsRequest> getCard;
    private final CardService<UpdateCardDTOResponse, UpdateCardDTORequest> updateCard;
    private final CardService<AddedAndSearchCardDTOResponse, SearchCardDTOCreditRequest> searchCard;


    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody AddCardDTORequest request) {
        var response = createCard.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCard(@RequestBody DeleteCardDTORequest request) {
        var response = deleteCard.execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCard(@RequestBody GetCardsRequest request) {
        var response = getCard.execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody UpdateCardDTORequest request) {
        var response = updateCard.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/searchCard")
    public ResponseEntity<?> searchCard(@RequestBody SearchCardDTOCreditRequest request) {
        var response = searchCard.execute(request);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
