package com.groupjn.authenticationservice.repository;

import com.groupjn.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String name);
}
