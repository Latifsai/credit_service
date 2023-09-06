package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.services.generators.DTOManagerCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerCreationServiceTest {

    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerUtil util;
    @InjectMocks
    private ManagerCreationService service;

    @Test
    public void testAddManagerServiceSuccess() {
        var request = new AddManagerRequest("Ivan", "Semyonov", "ivan_manager@loewen.de");
        var manager = EntityCreator.getManager();

        when(util.convertAddRequestToManager(request)).thenReturn(manager);
        when(repository.save(manager)).thenReturn(manager);
        when(util.convertManagerToResponse(manager)).thenReturn(DTOManagerCreator.getResponseDTO());

        assertEquals(DTOManagerCreator.getResponseDTO(), service.createManager(request));
    }

    @Test
    public void testAddManagerServiceValidationError() {
        var request = new AddManagerRequest(null, "", "ivan_manager@loewen.de");

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);

        assertEquals(3, set.size());
    }

}