package com.example.credit_service_project.fabrics.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.service.client.GetAllClientsService;

import java.util.UUID;

public interface ClientFabric {
    ClientService<ClientResponseDTO, AddClientRequest> addClient();
    GetAllClientsService getAllClients();
    ClientService<ClientResponseDTO, UUID> searchClient();
    ClientService<ClientResponseDTO, UpdateClientRequest> updateClient();
}
