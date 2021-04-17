package com.todolist.todolist.services;

import com.todolist.todolist.model.User;

public interface UserServices {
    User create(User user);
    User findByUsername (String username);
    User update(User user);
    void delete(User user);
    User findById(Long Id);
}
