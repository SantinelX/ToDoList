package com.todolist.todolist.services;

import com.todolist.todolist.exceptions.DatabaseConstrainException;
import com.todolist.todolist.model.Role;
import com.todolist.todolist.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository){
        try {
            this.roleRepository = roleRepository;
        } catch (Exception e) {
            throw new DatabaseConstrainException(e.getCause().getCause().getLocalizedMessage());
        }

    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRole(roleName);
    }
}
