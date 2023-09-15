package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.dto.user.UpdateClientRequest;
import com.example.credit_service_project.service.user.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users") //manager
@RequiredArgsConstructor
public class UserController {

    private final UserCreateService create;
    private final GetAllUsersService get;
    private final UserSearchService search;
    private final UserUpdateService update;
    private final UserDeleteService delete;


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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(name = "id") UUID id){
        delete.delete(id);
    }
}
