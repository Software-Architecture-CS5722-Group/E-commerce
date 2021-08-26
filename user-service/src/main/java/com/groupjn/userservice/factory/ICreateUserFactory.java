package com.groupjn.userservice.factory;


import com.groupjn.userservice.entity.User;
import com.groupjn.userservice.repository.UserRepository;
import com.groupjn.userservice.service.RoleServiceImpl;

public interface ICreateUserFactory {
    public void createUser(User user, UserRepository repository, RoleServiceImpl roleService);
}
