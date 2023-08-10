package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.repository.CardRepository;
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
        return repository.findAll().stream()
                .map(card -> util.convertCardToAddDTOResponse(card))
                .toList();
    }
}
