package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.fabrics.manager.FabricImp;
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

    private final FabricImp fabric;

    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<ManagerResponseDTO> getAllManagers() {
        return fabric.get().execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ManagerResponseDTO searchManager(@PathVariable @NotNull UUID id) {
        return fabric.search().execute(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ManagerResponseDTO addManager(@RequestBody AddManagerRequest request) {
        return fabric.add().execute(request);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ManagerResponseDTO addManager(@RequestBody UpdateManagerRequest request) {
        return fabric.update().execute(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerResponseDTO deleteManager(@PathVariable @NotNull UUID id) {
        return fabric.delete().execute(id);
    }
}
