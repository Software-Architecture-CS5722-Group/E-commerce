package com.groupjn.userservice.service;

import com.groupjn.userservice.entity.Role;
import com.groupjn.userservice.repository.RoleRepository;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository repository;

    @Override
    @OperationExecutionMonitoringProbe
    public List<Role> findRoleByName(String name) {
        return repository.findRoleByName(name);
    }
}
