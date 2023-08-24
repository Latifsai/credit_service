package com.example.credit_service_project.serviceTest.managerTest;

import com.example.credit_service_project.DTO.manager.AddManagerRequest;
import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.services.manager.AddManagerServiceImp;
import com.example.credit_service_project.services.utils.ManagerUtil;
import com.example.credit_service_project.serviceTest.generators.DTOManagerCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddManagerServiceImpTest {

    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerUtil util;
    @InjectMocks
    private AddManagerServiceImp service;

    @Test
    public void testAddManagerServiceSuccess() {
        var request = new AddManagerRequest("Ivan", "Semyonov", "ivan_manager@loewen.de");
        var manager = EntityCreator.getManager();

        when(util.convertAddRequestToManager(request)).thenReturn(manager);
        when(repository.save(manager)).thenReturn(manager);
        when(util.convertManagerToResponse(manager)).thenReturn(DTOManagerCreator.getResponseDTO());

        assertEquals(DTOManagerCreator.getResponseDTO(), service.execute(request));
    }

    @Test
    public void testAddManagerServiceValidationError() {
        var request = new AddManagerRequest(null, "", "ivan_manager@loewen.de");

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);

        assertEquals(3, set.size());
    }

}