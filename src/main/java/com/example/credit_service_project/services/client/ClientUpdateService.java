package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.services.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientUpdateService {

    private final ClientSearchService searchClientService;
    private final ClientCreateService addClientService;
    private final ClientUtil util;

    public ClientResponseDTO updateClient(UpdateClientRequest request) {
        Client client = searchClientService.findClientById(request.getId());
        Client updatedClient = util.updateClient(client, request);
        addClientService.saveClient(updatedClient);
        return util.convertClientToResponse(updatedClient);
    }
}
