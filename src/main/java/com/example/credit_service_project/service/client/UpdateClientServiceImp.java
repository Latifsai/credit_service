package com.example.credit_service_project.service.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.ClientNotFoundException;
import com.example.credit_service_project.service.utils.ClientUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateClientServiceImp implements ClientService<ClientResponseDTO, UpdateClientRequest> {

    private final SearchClientServiceImp searchClientService;
    private final AddClientServiceImp addClientService;
    private final ClientUtil util;


    @Override
    public ClientResponseDTO execute(UpdateClientRequest request) {
        Optional<Client> clientOptional = searchClientService.findClientById(request.getId());
        return clientOptional.map(client -> util.updateClient(client, request))
                .map(updatedClient -> {
                    addClientService.saveClient(updatedClient);
                    return util.convertClientToResponse(updatedClient);
                }).orElseThrow(() -> new ClientNotFoundException(ErrorsMessage.NOT_FOUND_CLIENT_MESSAGE));
    }
}
