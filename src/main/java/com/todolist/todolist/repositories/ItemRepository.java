package com.todolist.todolist.repositories;

import com.todolist.todolist.model.Item;
import com.todolist.todolist.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByItemStatus(ItemStatus itemStatus);
}
