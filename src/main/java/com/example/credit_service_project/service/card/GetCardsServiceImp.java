package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.GetCardsRequest;
import com.example.credit_service_project.DTO.cardDTO.GetCardsResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.exceptions.EmptyListException;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.utils.CardUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetCardsServiceImp implements CardService<List<GetCardsResponse>, GetCardsRequest> {

    private final CardRepository repository;
    private final CardUtils utils;
    @Override
    public List<GetCardsResponse> execute(GetCardsRequest request) {
        var cards = repository.findAllByAccount_AccountNumber(request.getAccountNumber());
        if (cards.isEmpty()) throw new EmptyListException(ErrorsMessage.EMPTY_CARD_LIST_MESSAGE);
        return cards.stream()
                .map(card -> utils.convertCardToGetCardResponse(card))
                .toList();
    }
}
