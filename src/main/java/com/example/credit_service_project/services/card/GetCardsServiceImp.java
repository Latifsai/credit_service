package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.services.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class GetCardsServiceImp  {

    private final CardRepository repository;
    private final CardUtil util;

    @Transactional(readOnly = true)
    public List<CardDTOResponse> execute() {
        return repository.findAll().stream()
                .map(util::convertCardToAddDTOResponse)
                .toList();
    }
}
