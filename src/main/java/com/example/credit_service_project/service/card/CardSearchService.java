package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.utils.CardUtil;
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

    /**
     * Method upon id will find a Card and convert it to response
     * @param id UUID
     * @return CardResponseDTO
     */
    @Transactional(readOnly = true)
    public CardResponseDTO searchCard(UUID id) {
        Card card = findCardById(id);
        log.info("Search card with ID: {}", card.getId());
        return utils.convertCardToAddDTOResponse(card);
    }

    /**
     * Find Card by ID
     * @param id UUID
     * @return Card
     */
    public Card findCardById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }

    /**
     * Here will be searched the Card by belongs Account
     * @param account Account
     * @return Card
     */
    public Card findByAccount(Account account) {
        return repository.findByAccount(account).stream()
                .filter(card -> card.getCardStatus().equals(CardStatus.ACTIVE))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }
}
