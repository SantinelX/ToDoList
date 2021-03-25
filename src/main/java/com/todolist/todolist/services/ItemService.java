package com.todolist.todolist.services;

import com.todolist.todolist.model.Item;
import com.todolist.todolist.model.ItemStatus;

import java.util.List;

public interface ItemService {
    Item save(Item item);
    Item findById(Long id);
    void delete (Long id);
    List<Item> findAllItemsByStatus(ItemStatus itemStatus);
    List<Item>findAll();

}
