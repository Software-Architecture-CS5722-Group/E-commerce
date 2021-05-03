package com.softwarearchitecture.user.service.factory;

import com.softwarearchitecture.user.service.entity.Role;
import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.repository.UserRepository;
import com.softwarearchitecture.user.service.service.RoleServiceImpl;

import java.util.List;

public class AdminUser implements ICreateUserFactory{

    @Override
    public void createUser(User user, UserRepository repository, RoleServiceImpl roleService) {
        List<Role> roles = roleService.findRoleByName("ROLE_admin");
        user.setRoles(roles);
        repository.save(user);

    }
}
