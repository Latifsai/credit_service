package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.CardNotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCardServiceImp implements CardService<CardDTOResponse, AddCardDTORequest> {

    private final CardRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;
    private final CardUtil util;

    @Override
    public CardDTOResponse execute(AddCardDTORequest request) {
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        if (account.isPresent()) {
            Card card = util.convertAddRequestToEntity(request, account.get());
            saveCard(card);
            return util.convertCardToAddDTOResponse(card);
        }
        throw new CardNotFoundException(ErrorsMessage.UNABLE_TO_ADD_CARD_MESSAGE);
    }

    public Card saveCard(Card card) {
        return repository.save(card);
    }
}
