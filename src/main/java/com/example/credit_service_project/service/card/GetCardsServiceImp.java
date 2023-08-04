package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.errors.exceptions.EmptyListException;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetCardsServiceImp  {

    private final CardRepository repository;
    private final CardUtil util;

    public List<CardDTOResponse> execute() {
        var cards = repository.findAll();
        if (cards.isEmpty()) throw new EmptyListException(ErrorsMessage.EMPTY_CARD_LIST_MESSAGE);
        return cards.stream()
                .map(card -> util.convertCardToAddDTOResponse(card))
                .toList();
    }
}
