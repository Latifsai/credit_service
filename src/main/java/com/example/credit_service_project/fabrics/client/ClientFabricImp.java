package com.example.credit_service_project.fabrics.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.service.client.AddClientServiceImp;
import com.example.credit_service_project.service.client.GetAllClientsService;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.service.client.UpdateClientServiceImp;
import com.example.credit_service_project.service.manager.SearchManagerServiceImp;
import com.example.credit_service_project.service.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientFabricImp implements ClientFabric {

    private final ClientRepository repository;
    private final SearchManagerServiceImp searchManagerService;
    private final SearchClientServiceImp searchClientService;
    private final AddClientServiceImp addClientService;
    private final ClientUtil util;

    @Override
    public ClientService<ClientResponseDTO, AddClientRequest> addClient() {
        return new AddClientServiceImp(repository, searchManagerService, util);
    }

    @Override
    public GetAllClientsService getAllClients() {
        return new GetAllClientsService(repository, util);
    }

    @Override
    public ClientService<ClientResponseDTO, UUID> searchClient() {
        return new SearchClientServiceImp(repository, util);
    }

    @Override
    public ClientService<ClientResponseDTO, UpdateClientRequest> updateClient() {
        return new UpdateClientServiceImp(searchClientService,addClientService, util);
    }
}
