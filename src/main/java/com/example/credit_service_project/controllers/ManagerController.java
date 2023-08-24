package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.services.manager.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final AddManagerServiceImp create;
    private final DeleteManagerServiceImp delete;
    private final GetAllManagersService getAll;
    private final SearchManagerServiceImp search;
    private final UpdateMangerServiceImp update;

    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<ManagerResponseDTO> getAllManagers() {
        return getAll.execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ManagerResponseDTO searchManager(@PathVariable("id") @NotNull UUID id) {
        return search.execute(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerResponseDTO addManager(@RequestBody AddManagerRequest request) {
        return create.execute(request);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerResponseDTO addManager(@RequestBody UpdateManagerRequest request) {
        return update.execute(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ManagerResponseDTO deleteManager(@PathVariable @NotNull UUID id) {
        return delete.execute(id);
    }
}
