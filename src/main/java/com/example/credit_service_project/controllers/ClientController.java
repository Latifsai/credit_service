package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.DTO.client.UpdateClientRequest;
import com.example.credit_service_project.fabrics.client.ClientFabricImp;
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

    private final ClientFabricImp fabric;


    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<ClientResponseDTO> getAllClients() {
        return fabric.getAllClients().execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ClientResponseDTO searchManager(@PathVariable @NotNull UUID id) {
        return fabric.searchClient().execute(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO addManager(@RequestBody AddClientRequest request) {
        return fabric.addClient().execute(request);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO addManager(@RequestBody UpdateClientRequest request) {
        return fabric.updateClient().execute(request);
    }

}
