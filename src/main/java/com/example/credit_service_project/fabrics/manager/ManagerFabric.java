package com.example.credit_service_project.fabrics.manager;

import com.example.credit_service_project.service.manager.*;

public interface ManagerFabric {
    AddManagerServiceImp add();
    DeleteManagerServiceImp delete();
    GetAllManagersService get();
    SearchManagerServiceImp search();
    UpdateMangerServiceImp update();
}
