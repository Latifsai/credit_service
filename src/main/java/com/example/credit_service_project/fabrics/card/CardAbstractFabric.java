package com.example.credit_service_project.fabrics.card;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.card.GetCardsServiceImp;

import java.util.UUID;

public interface CardAbstractFabric {
    CardService<CardDTOResponse, AddCardDTORequest> activateCreateCard();

    GetCardsServiceImp getCardsService();

    CardService<CardDTOResponse, UpdateCardDTORequest> activateUpdateCard();

    CardService<CardDTOResponse, UUID> activateSearchCard();
}
