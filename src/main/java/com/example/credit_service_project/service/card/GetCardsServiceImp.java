package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.GetCardsRequest;
import com.example.credit_service_project.DTO.cardDTO.GetCardsResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.exeption.EmptyListException;
import com.example.credit_service_project.service.exeption.ErrorsMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class GetCardsServiceImp implements CardService<GetCardsResponse, GetCardsRequest> {

    private final CardRepository repository;


    @Override
    public GetCardsResponse execute(GetCardsRequest request) {
        var cards = repository.findAllByAccount_AccountNumber(request.getAccountNumber());
        if (cards.isEmpty()) throw new EmptyListException(ErrorsMessage.EMPTY_CARD_LIST_MESSAGE);
        return new GetCardsResponse(cards);
    }
}
