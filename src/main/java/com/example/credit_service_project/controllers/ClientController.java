package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.user.CreateUserRequest;
import com.example.credit_service_project.DTO.user.UserResponseDTO;
import com.example.credit_service_project.DTO.user.UpdateClientRequest;
import com.example.credit_service_project.services.user.UserCreateService;
import com.example.credit_service_project.services.user.GetAllUsersService;
import com.example.credit_service_project.services.user.UserSearchService;
import com.example.credit_service_project.services.user.UserUpdateService;
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

    private final UserCreateService create;
    private final GetAllUsersService get;
    private final UserSearchService search;
    private final UserUpdateService update;


    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseDTO> getAllClients() {
        return get.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponseDTO searchClient(@PathVariable("id") @NotNull UUID id) {
        return search.searchUser(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createClient (@RequestBody CreateUserRequest request) {
        return create.createClient(request);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO updateClient(@RequestBody UpdateClientRequest request) {
        return update.updateClient(request);
    }

}
