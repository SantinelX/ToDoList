package com.todolist.todolist.services;

import com.todolist.todolist.model.Role;


public interface RoleService {
    Role create(Role role);
    Role findByRoleName(String roleName);
}
