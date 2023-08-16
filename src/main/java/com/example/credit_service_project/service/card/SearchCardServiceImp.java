package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.CardNotFoundException;
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
        Card card = findCardById(id);
        return utils.convertCardToAddDTOResponse(card);
    }

    public Card findCardById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }

    public Card findByAccount(Account account) {
        return repository.findByAccount(account)
                .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }
}
