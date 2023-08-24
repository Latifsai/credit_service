package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ClientUtil {

    public Client convertAddRequestToEntity(AddClientRequest request, Manager manager) {
        var client = new Client();
        client.setManager(manager);
        client.setName(request.getName());
        client.setSurname(request.getSurname());
        client.setSalary(request.getSalary());
        client.setPassiveIncome(request.getPassiveIncome());
        client.setExpenses(request.getExpenses());
        client.setAddress(request.getAddress());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setRegistrationDate(LocalDate.now());
        client.setUpdateDate(LocalDate.now());
        return client;
    }

    public ClientResponseDTO convertClientToResponse(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getManager().getId(),
                client.getManager().getEmail(),
                client.getName(),
                client.getSurname(),
                client.getSalary().add(client.getPassiveIncome()),
                client.getExpenses(),
                client.getAddress(),
                client.getEmail(),
                client.getPhone(),
                client.getRegistrationDate(),
                client.getUpdateDate());
    }

    public Client updateClient(Client client, UpdateClientRequest request) {
        if (checkInput(request.getSalary())) client.setSalary(request.getSalary());
        if (checkInput(request.getPassiveIncome())) client.setPassiveIncome(request.getPassiveIncome());
        if (checkInput(request.getExpenses())) client.setExpenses(request.getExpenses());
        if (checkForStrings(request.getAddress())) client.setAddress(request.getAddress());
        if (checkForStrings(request.getEmail())) client.setEmail(request.getEmail());
        if (checkForStrings(request.getPhone())) client.setPhone(request.getPhone());
        return client;
    }

    private boolean checkInput(BigDecimal input) {
        return input != null;
    }
    private boolean checkForStrings(String criteria) {
        return criteria != null && !criteria.trim().isEmpty();
    }
}

