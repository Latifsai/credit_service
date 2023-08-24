package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.services.ClientService;
import com.example.credit_service_project.services.utils.ClientUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateClientServiceImp implements ClientService<ClientResponseDTO, UpdateClientRequest> {

    private final SearchClientServiceImp searchClientService;
    private final AddClientServiceImp addClientService;
    private final ClientUtil util;


    @Override
    public ClientResponseDTO execute(UpdateClientRequest request) {
        Client client = searchClientService.findClientById(request.getId());
        Client updatedClient = util.updateClient(client, request);
        addClientService.saveClient(updatedClient);
        return util.convertClientToResponse(updatedClient);
    }
}
