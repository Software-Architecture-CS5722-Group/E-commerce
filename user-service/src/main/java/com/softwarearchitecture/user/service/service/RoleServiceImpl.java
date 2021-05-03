package com.softwarearchitecture.user.service.service;

import com.softwarearchitecture.user.service.entity.Role;
import com.softwarearchitecture.user.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findRoleByName(String name) {
        return repository.findRoleByName(name);
    }
}
