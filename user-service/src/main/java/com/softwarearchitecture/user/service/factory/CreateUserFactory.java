package com.softwarearchitecture.user.service.factory;

import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.repository.UserRepository;
import com.softwarearchitecture.user.service.service.RoleServiceImpl;

public class CreateUserFactory {

    private User user;

    private UserRepository repository;

    private RoleServiceImpl roleService;

    public CreateUserFactory(User user, UserRepository repository, RoleServiceImpl roleService){
        this.user = user;
        this.repository = repository;
        this.roleService = roleService;
    }

    public User createUser(){
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
        return user;
    }



}
