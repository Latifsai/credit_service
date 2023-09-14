package com.example.credit_service_project.services.card;

import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardCreateService {

    private final CardRepository repository;
    private final AccountSearchService accountSearchService;
    private final CardUtil util;

    public CardResponseDTO createCard(CreateCardRequestDTO request) {
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Card card = util.convertCreateRequestToEntity(request, account);
        Card savedCard = saveCard(card);
        log.info("Create and save card with IBAN: {}", savedCard.getIBAN());
        return util.convertCardToAddDTOResponse(savedCard);
    }

    public Card saveCard(Card card) {
        return repository.save(card);
    }
}
