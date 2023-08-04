package com.example.credit_service_project.service.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.manager.SearchManagerServiceImp;
import com.example.credit_service_project.service.utils.ClientUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AddClientServiceImp implements ClientService<ClientResponseDTO, AddClientRequest> {

    private final ClientRepository repository;
    private final SearchManagerServiceImp searchManagerService;
    private final ClientUtil util;

    @Override
    public ClientResponseDTO execute(AddClientRequest request) {
        var manager = searchManagerService.findManagerById(request.getManagerID());
        if (manager.isPresent()){
            Client client = util.convertAddRequestToEntity(request, manager.get());
            repository.save(client);
            return util.convertClientToResponse(client);
        }
        throw new NotFoundException(ErrorsMessage.NOT_FOUND_MANAGER_MESSAGE);
    }

}
