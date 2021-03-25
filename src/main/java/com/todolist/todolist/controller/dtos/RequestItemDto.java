package com.todolist.todolist.controller.dtos;
import com.todolist.todolist.model.ItemStatus;

// DTO - DATA TRANSFER OBJECT
public class RequestItemDto {
    private String name;
    private String description;
    private ItemStatus itemStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }
}
