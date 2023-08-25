package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.services.client.ClientCreateService;
import com.example.credit_service_project.services.client.GetAllClientsService;
import com.example.credit_service_project.services.client.ClientSearchService;
import com.example.credit_service_project.services.client.ClientUpdateService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientCreateService create;
    private final GetAllClientsService get;
    private final ClientSearchService search;
    private final ClientUpdateService update;


    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<ClientResponseDTO> getAllClients() {
        return get.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ClientResponseDTO searchManager(@PathVariable("id") @NotNull UUID id) {
        return search.searchClient(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO addManager(@RequestBody AddClientRequest request) {
        return create.createClient(request);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO addManager(@RequestBody UpdateClientRequest request) {
        return update.updateClient(request);
    }

}
