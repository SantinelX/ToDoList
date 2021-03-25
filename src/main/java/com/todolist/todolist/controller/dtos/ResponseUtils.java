package com.todolist.todolist.controller.dtos;

import com.todolist.todolist.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ResponseUtils {

    public static ResponseItemDto mapItemToResponseIteDto(Item item){
        ResponseItemDto responseItemDto = new ResponseItemDto(item.getId(), item.getName(), item.getDescription(), item.getItemStatus());

        if(item.getName() != null){
            responseItemDto.setAssignedToItemGroupId(item.getItemGroup().getId());
            responseItemDto.setAssignedToItemGroupName(item.getItemGroup().getItemGroupName());
        }
        return responseItemDto;
    }

    public static List<ResponseItemDto> mapItemToResponseItemDtoList(List<Item> items) {

        List<ResponseItemDto> responseItemList =new ArrayList<>();
        items.forEach(item -> {ResponseItemDto responseItemDto = mapItemToResponseIteDto(item);
        responseItemList.add(responseItemDto);
        });
     return responseItemList;
    }

}
