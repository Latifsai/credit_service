package com.example.credit_service_project.serviceTest.managerTest;

import com.example.credit_service_project.DTO.manager.UpdateManagerRequest;
import com.example.credit_service_project.repositories.ManagerRepository;
import com.example.credit_service_project.services.manager.ManagerUpdateService;
import com.example.credit_service_project.services.utils.ManagerUtil;
import com.example.credit_service_project.serviceTest.generators.DTOManagerCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerUpdateServiceTest {
    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerUtil util;
    @InjectMocks
    private ManagerUpdateService service;

    @Test
    public void testDeleteManagerServiceSuccess() {
        var request = new UpdateManagerRequest(UUID.randomUUID(), "Simonov", "main_manager@loewen.de");
        var manager = EntityCreator.getManager();
        var updatedManager = EntityCreator.getUpdatedManager();
        when(repository.findById(request.getId())).thenReturn(Optional.of(manager));
        when(util.update(manager, request)).thenReturn(updatedManager);
        when(util.convertManagerToResponse(updatedManager)).thenReturn(DTOManagerCreator.getUpdatedResponseDTO());

        assertEquals(DTOManagerCreator.getUpdatedResponseDTO(), service.updateManager(request));
    }

    @Test
    public void testDeleteManagerServiceNotFoundException() {
        var request = new UpdateManagerRequest(UUID.randomUUID(), "Simonov", "main_manager@loewen.de");
        when(repository.findById(request.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.updateManager(request));
    }

    @Test
    public void testDeleteManagerServiceValidation() {
        var request = new UpdateManagerRequest(null, "", "main_manage");

        var validation  = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validation.validate(request);
        assertEquals(3, set.size());
    }



}