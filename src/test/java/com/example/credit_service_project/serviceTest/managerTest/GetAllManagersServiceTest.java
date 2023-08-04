package com.example.credit_service_project.serviceTest.managerTest;

import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.manager.GetAllManagersService;
import com.example.credit_service_project.service.utils.ManagerUtil;
import com.example.credit_service_project.serviceTest.generators.DTOManagerCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllManagersServiceTest {
    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerUtil util;
    @InjectMocks
    private GetAllManagersService service;

    @Test
    public void testDeleteManagerServiceSuccess() {

        var manager = EntityCreator.getManager();

        when(repository.findAll()).thenReturn(java.util.List.of(manager));
        when(util.convertManagerToResponse(manager)).thenReturn(DTOManagerCreator.getResponseDTO());

        assertEquals(List.of(DTOManagerCreator.getResponseDTO()), service.execute());
    }

    @Test
    public void testDeleteManagerServiceNotFoundException() {


        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), service.execute());
    }
}