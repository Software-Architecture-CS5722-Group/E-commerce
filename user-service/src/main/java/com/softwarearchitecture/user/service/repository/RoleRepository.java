package com.softwarearchitecture.user.service.repository;

import com.softwarearchitecture.user.service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT role FROM Role role where role.name=:name")
    List<Role> findRoleByName(@Param("name") String name);
}
