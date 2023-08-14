package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.CardNotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchCardServiceImp implements CardService<CardDTOResponse, UUID> {

    private final CardRepository repository;
    private final CardUtil utils;

    @Override
    public CardDTOResponse execute(UUID id) {
        Optional<Card> card = findCardById(id);
        return card.map(c -> utils.convertCardToAddDTOResponse(c))
                .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }

    public Optional<Card> findCardById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Card> findByAccount(Account account) {
        return repository.findByAccount(account);
    }
}
