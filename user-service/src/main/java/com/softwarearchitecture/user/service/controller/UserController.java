package com.softwarearchitecture.user.service.controller;

import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.factory.CreateUserFactory;
import com.softwarearchitecture.user.service.repository.UserRepository;
import com.softwarearchitecture.user.service.service.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User createUserDTO) throws Exception{
        User user;
        try{
            CreateUserFactory createUserFactory = new CreateUserFactory(createUserDTO,repository,roleService);
            return  createUserFactory.createUser();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        return new User();

    }

    @GetMapping("/allUser")
    public List<User> users(){
        return repository.findAll();
    }

}
