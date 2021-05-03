package com.softwarearchitecture.user.service.service;


import com.softwarearchitecture.user.service.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findRoleByName(String name);
}
