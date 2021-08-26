package com.groupjn.userservice.service;


import com.groupjn.userservice.entity.Role;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;

import java.util.List;

public interface IRoleService {
    @OperationExecutionMonitoringProbe
    List<Role> findRoleByName(String name);
}
