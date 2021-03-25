package com.todolist.todolist.services;

import com.todolist.todolist.model.ItemGroup;

import java.util.List;

public interface ItemGroupService {
    ItemGroup save(ItemGroup itemGroup);
    void delete(Long id);
    List<ItemGroup> findAll();
    ItemGroup findById(Long id);
}
