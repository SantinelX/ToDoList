package com.todolist.todolist.repositories;

import com.todolist.todolist.model.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemGroupRepository extends JpaRepository <ItemGroup, Long> {

}
