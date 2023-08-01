package com.example.credit_service_project.fabrics.card;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.service.CardService;

import java.util.List;

public interface CardAbstractFabric {
    CardService<AddedAndSearchCardDTOResponse, AddCardDTORequest> activateCreateCard();
    CardService<DeleteCardDTOResponse, DeleteCardDTORequest> activateDeleteCard();
    CardService<List<GetCardsResponse>, GetCardsRequest> activateGetCard();
    CardService<UpdateCardDTOResponse, UpdateCardDTORequest> activateUpdateCard();
    CardService<AddedAndSearchCardDTOResponse, SearchCardDTOCreditRequest> activateSearchCard();
}
