package com.todolist.todolist.repositories;

import com.todolist.todolist.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository <Role, Long> {
    Role findByRole(String rolName);
}
