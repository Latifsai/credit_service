package com.example.credit_service_project.services.manager;

import com.example.credit_service_project.DTO.manager.ManagerResponseDTO;
import com.example.credit_service_project.repositories.ManagerRepository;
import com.example.credit_service_project.services.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllManagersService {

    private final ManagerRepository repository;
    private final ManagerUtil util;

    @Transactional(readOnly = true)
    public List<ManagerResponseDTO> execute() {
        log.info("Get a list of Managers");
        return repository.findAll().stream()
                .map(util::convertManagerToResponse)
                .toList();
    }
}
