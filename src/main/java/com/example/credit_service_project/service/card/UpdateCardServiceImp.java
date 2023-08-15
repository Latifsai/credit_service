package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AccountNotFoundException;
import com.example.credit_service_project.validation.exceptions.CardNotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateCardServiceImp implements CardService<CardDTOResponse, UpdateCardDTORequest> {

    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardServiceImp;
    private final SearchAccountsServiceImp searchAccountsService;
    private final UpdateAccountServiceImp updateAccountService;
    private final CardUtil utils;


    @Override
    public CardDTOResponse execute(UpdateCardDTORequest request) {
        Card card = searchCardService.findCardById(request.getId())
                .orElseThrow(() -> new CardNotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));

        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        Card updatedCard = utils.updateCard(card, request);
        updateCard(updatedCard);

        Account updatedAccount = utils.updateAccountBalance(account, card);
        updateAccountService.saveUpdatedAccount(updatedAccount);

        return utils.convertCardToAddDTOResponse(updatedCard);
    }

    public void updateCard(Card updatedCard) {
        createCardServiceImp.saveCard(updatedCard);
    }
}
