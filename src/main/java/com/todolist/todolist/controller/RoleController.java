package com.todolist.todolist.controller;

import com.todolist.todolist.model.Role;
import com.todolist.todolist.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping(path = "/roles")
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @GetMapping(path = "/roles/{roleName}")
    public Role findRoleName(@PathVariable String rolename) {
        return roleService.findByRoleName(rolename);

    }
}
