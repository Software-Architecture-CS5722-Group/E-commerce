package com.softwarearchitecture.user.service.factory;


import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.repository.UserRepository;
import com.softwarearchitecture.user.service.service.RoleServiceImpl;

public interface ICreateUserFactory {
    public void createUser(User user, UserRepository repository, RoleServiceImpl roleService);
}
