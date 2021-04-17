package com.todolist.todolist.controller;

import com.todolist.todolist.controller.dtos.RequestUserDto;
import com.todolist.todolist.model.Role;
import com.todolist.todolist.model.User;
import com.todolist.todolist.services.RoleService;
import com.todolist.todolist.services.UserServices;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    private UserServices userServices;
    private RoleService roleService;

    @Autowired
    public UserController(UserServices userServices, RoleService roleService){
        this.roleService = roleService;
        this.userServices = userServices;
    }

    @PostMapping(path = "/register")
    public User create(@RequestBody RequestUserDto requestUserDto){
        User user = mapRequestUserDtoToUser(requestUserDto);
        return userServices.create(user);
    }

    public User mapRequestUserDtoToUser(RequestUserDto requestUserDto){
        User user = new User();
        user.setUsername((requestUserDto.getUserName()));
        user.setPassword(requestUserDto.getPassword());
        Role role = roleService.findByRoleName(requestUserDto.getRoleName());
        if (role == null){
            throw new ResourceNotFoundException(String.format("Role with name %s not found !!!", requestUserDto ));
        }
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        return user;
    }
}
