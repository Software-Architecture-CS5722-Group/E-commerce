package com.groupjn.userservice.service;

import com.groupjn.userservice.entity.User;
import com.groupjn.userservice.repository.UserRepository;
import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository repository;

    @Override
    @OperationExecutionMonitoringProbe
    public User save(User user) {
        return repository.save(user);
    }
}
