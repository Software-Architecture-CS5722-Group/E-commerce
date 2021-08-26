package com.groupjn.userservice.factory;

import com.groupjn.userservice.entity.User;
import com.groupjn.userservice.repository.UserRepository;
import com.groupjn.userservice.service.RoleServiceImpl;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.controller.MonitoringController;

public class CreateUserFactory {

    private User user;

    private UserRepository repository;

    private RoleServiceImpl roleService;

    private final IMonitoringController MONITORING_CONTROLLER =
            MonitoringController.getInstance();

    public CreateUserFactory(User user, UserRepository repository, RoleServiceImpl roleService){
        this.user = user;
        this.repository = repository;
        this.roleService = roleService;
    }

    public User createUser(){
        final long tin = MONITORING_CONTROLLER.getTimeSource().getTime();
        switch (user.getUserType()){
            case ADMIN:
                AdminUser adminUser = new AdminUser();
                adminUser.createUser(user,repository,roleService);
                break;
            case CLIENT:
            default:
                ClientUser clientUser = new ClientUser();
                clientUser.createUser(user,repository,roleService);
                break;
        }
        final long tout = MONITORING_CONTROLLER.getTimeSource().getTime();

        final OperationExecutionRecord e = new OperationExecutionRecord(
                "public User " + CreateUserFactory.class.getName()+ ".createUser(...)",
                OperationExecutionRecord.NO_SESSION_ID,
                OperationExecutionRecord.NO_TRACE_ID,
                tin, tout, "myHost",
                OperationExecutionRecord.NO_EOI_ESS,
                OperationExecutionRecord.NO_EOI_ESS);
        MONITORING_CONTROLLER.newMonitoringRecord(e);
        return user;
    }



}
