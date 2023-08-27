package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.services.utils.CardUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardSearchService {

    private final CardRepository repository;
    private final CardUtil utils;

    @Transactional(readOnly = true)
    public CardDTOResponse searchCard(UUID id) {
        Card card = findCardById(id);
        log.info("Search card with ID: {}", card.getId());
        return utils.convertCardToAddDTOResponse(card);
    }

    public Card findCardById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }

    public Card findByAccount(Account account) {
        return repository.findByAccount(account)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }
}
