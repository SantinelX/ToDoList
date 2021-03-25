package com.todolist.todolist.services;

import com.todolist.todolist.model.ItemGroup;
import com.todolist.todolist.repositories.ItemGroupRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemGroupServiceImplementation implements ItemGroupService {

    private ItemGroupRepository itemGroupRepository;

    public ItemGroupServiceImplementation(ItemGroupRepository itemGroupRepository) {
        this.itemGroupRepository = itemGroupRepository;
    }

    @Override
    public ItemGroup save(ItemGroup itemGroup) {
        return itemGroupRepository.save(itemGroup);
    }

    @Override
    public void delete(Long id) {
        ItemGroup itemGroup = findById(id);
        itemGroupRepository.delete(itemGroup);
    }

    @Override
    public List<ItemGroup> findAll() {
        return itemGroupRepository.findAll();
    }

    @Override
    public ItemGroup findById(Long id) {
        Optional<ItemGroup> optionalItemGroup = itemGroupRepository.findById(id);
        if (optionalItemGroup.isPresent()) {
            return optionalItemGroup.get();
        } else {
            throw new ResourceNotFoundException(String.format("Resource of type ItemGroup with id %d does not exist", id));
        }
    }
}
