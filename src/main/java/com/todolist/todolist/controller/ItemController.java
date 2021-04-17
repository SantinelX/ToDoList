package com.todolist.todolist.controller;
import com.todolist.todolist.controller.dtos.RequestItemDto;
import com.todolist.todolist.controller.dtos.RequestUtils;
import com.todolist.todolist.controller.dtos.ResponseItemDto;
import com.todolist.todolist.controller.dtos.ResponseUtils;
import com.todolist.todolist.model.Item;
import com.todolist.todolist.model.ItemStatus;
import com.todolist.todolist.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {

    private ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "/items")
public ResponseItemDto create(@RequestBody RequestItemDto itemDto){

        Item itemToBeSaved = RequestUtils.mapItemDtoToItem(itemDto);
        return ResponseUtils.mapItemToResponseIteDto(itemService.save(itemToBeSaved));
    }

    @GetMapping(path = "/items")
    public List<ResponseItemDto> getAll(){
        return ResponseUtils.mapItemToResponseItemDtoList(itemService.findAll());
    }

    // http://localhost:8080/items/5
    @GetMapping(path = "/items/{id}")
    public ResponseItemDto findById(@PathVariable("id") Long id){
        return ResponseUtils.mapItemToResponseIteDto(itemService.findById(id));
    }

    @PutMapping(path = "/items/{id}")
    public ResponseItemDto updateItem(@PathVariable("id") Long id, @RequestBody RequestItemDto requestItemDto){
        Item updatedItem = updateItemFromRequestItemDto(itemService.findById(id),requestItemDto); // se updateaza obiectul
        return ResponseUtils.mapItemToResponseIteDto(itemService.save(updatedItem)); // se salveaza obiectul updatat in baza de date si se intoarce inapoi in ui
    }


    @DeleteMapping(path = "/items/{id}")
    public HttpStatus deleteitem(@PathVariable("id") Long id){
        itemService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/item/search")
    public List<ResponseItemDto> searchItems (@RequestParam("itemStatus")ItemStatus itemStatus){
        return ResponseUtils.mapItemToResponseItemDtoList(itemService.findAllItemsByStatus(itemStatus));
    }

    private Item updateItemFromRequestItemDto (Item dbItem, RequestItemDto requestItemDto){
        if(requestItemDto.getName() != null) {
            dbItem.setName(requestItemDto.getName());
        } if(requestItemDto.getDescription() != null) {
            dbItem.setName(requestItemDto.getDescription());
        } if(requestItemDto.getItemStatus() != null) {
            dbItem.setItemStatus(requestItemDto.getItemStatus());
            if(requestItemDto.getItemStatus().equals(ItemStatus.NOT_STARTED)) {
                dbItem.setStartDate(null);
                dbItem.setEndDate(null);
            }
            if(requestItemDto.getItemStatus().equals(ItemStatus.IN_PROGRESS)) {
                if (dbItem.getStartDate() == null) {
                    dbItem.setStartDate(new Date());
                }
                dbItem.setEndDate(null);
            }
            if(requestItemDto.getItemStatus().equals(ItemStatus.DONE)) {
                dbItem.setEndDate(new Date());
            }
        } return dbItem;
    }
}
