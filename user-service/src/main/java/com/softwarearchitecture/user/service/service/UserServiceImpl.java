package com.softwarearchitecture.user.service.service;

import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
