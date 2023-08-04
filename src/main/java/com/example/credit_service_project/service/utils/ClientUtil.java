package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ClientUtil {

    public Client convertAddRequestToEntity(AddClientRequest request, Manager manager) {
        var client = new Client();
        client.setManager(manager);
        client.setName(request.getName());
        client.setSurname(request.getSurname());
        client.setIncome(request.getIncome());
        client.setExpenses(request.getExpenses());
        return client;
    }

    public ClientResponseDTO convertClientToResponse(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getManager().getId(),
                client.getManager().getEmail(),
                client.getName(),
                client.getSurname(),
                client.getIncome(),
                client.getExpenses());
    }

    public Client updateClient(Client client, UpdateClientRequest request) {
        if (checkInput(client.getIncome())) client.setIncome(request.getIncome());
        if (checkInput(client.getExpenses())) client.setExpenses(request.getExpenses());
        return client;
    }

    private boolean checkInput(BigDecimal input) {
        return input != null;
    }
}

