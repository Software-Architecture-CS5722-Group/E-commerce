package com.softwarearchitecture.user.service.factory;

import com.softwarearchitecture.user.service.entity.Role;
import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.repository.RoleRepository;
import com.softwarearchitecture.user.service.repository.UserRepository;
import com.softwarearchitecture.user.service.service.RoleServiceImpl;
import com.softwarearchitecture.user.service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClientUser implements ICreateUserFactory{


    @Override
    public void createUser(User user, UserRepository repository, RoleServiceImpl roleService) {
        List<Role> roles = roleService.findRoleByName("ROLE_operator");
        user.setRoles(roles);
        repository.save(user);
    }
}
