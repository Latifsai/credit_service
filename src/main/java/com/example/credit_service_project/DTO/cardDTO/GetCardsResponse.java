package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.Card;
import lombok.Value;

import java.util.List;

@Value
public class GetCardsResponse {
    List<Card> cards;
}
