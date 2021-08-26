package com.groupjn.userservice.factory;

import com.groupjn.userservice.entity.Role;
import com.groupjn.userservice.entity.User;
import com.groupjn.userservice.repository.UserRepository;
import com.groupjn.userservice.service.RoleServiceImpl;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;

import java.util.List;

public class AdminUser implements ICreateUserFactory{

    @Override
    @OperationExecutionMonitoringProbe
    public void createUser(User user, UserRepository repository, RoleServiceImpl roleService) {
        List<Role> roles = roleService.findRoleByName("ROLE_admin");
        user.setRoles(roles);
        repository.save(user);

    }
}
