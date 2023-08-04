package com.example.credit_service_project.fabrics.manager;

import com.example.credit_service_project.repository.ManagerRepository;
import com.example.credit_service_project.service.manager.*;
import com.example.credit_service_project.service.utils.ManagerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FabricImp implements ManagerFabric{

    private final ManagerUtil util;
    private final ManagerRepository repository;

    @Override
    public AddManagerServiceImp add() {
        return new AddManagerServiceImp(repository,util);
    }

    @Override
    public DeleteManagerServiceImp delete() {
        return new DeleteManagerServiceImp(util, repository);
    }

    @Override
    public GetAllManagersService get() {
        return new GetAllManagersService(repository, util);
    }

    @Override
    public SearchManagerServiceImp search() {
        return new SearchManagerServiceImp(repository, util);
    }

    @Override
    public UpdateMangerServiceImp update() {
        return new UpdateMangerServiceImp(repository, util);
    }
}
