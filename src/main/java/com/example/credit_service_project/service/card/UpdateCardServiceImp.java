package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateCardServiceImp implements CardService<CardDTOResponse, UpdateCardDTORequest> {

    private final CardRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;
    private final UpdateAccountServiceImp updateAccountService;
    private final CardUtil utils;


    @Override
    public CardDTOResponse execute(UpdateCardDTORequest request) {
        Optional<Card> card = repository.findById(request.getId());
        Optional<Account> account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        if (card.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE);
        if (account.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE);

        var updatedCard = utils.updateCard(card.get(), request);
        updateCard(updatedCard);

        var updatedAccount = utils.updateAccountBalance(account.get(), card.get());
        updateAccountService.saveUpdatedAccount(updatedAccount);
        return utils.convertCardToAddDTOResponse(updatedCard);
    }

    public void updateCard(Card updatedCard) {
        repository.save(updatedCard);
    }
}
