package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.SearchCardDTOCreditRequest;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchCardServiceImp implements CardService<CardDTOResponse, SearchCardDTOCreditRequest> {

    private final CardRepository repository;
    private final CardUtil utils;

    @Override
    public CardDTOResponse execute(SearchCardDTOCreditRequest request) {
        Optional<Card> card = findCardByIdAndNumber(request.getId(), request.getCardNumber());
        return card.map(c -> utils.convertCardToAddDTOResponse(c))
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }

    public Optional<Card> findCardByIdAndNumber(UUID id, String number) {
        return repository.findByIdAndCardNumber(id, number);
    }
}
