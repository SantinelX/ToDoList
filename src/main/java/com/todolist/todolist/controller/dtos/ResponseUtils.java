package com.todolist.todolist.controller.dtos;

import com.todolist.todolist.model.Item;
import com.todolist.todolist.model.ItemStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResponseUtils {

    public static ResponseItemDto mapItemToResponseIteDto(Item item){
        ResponseItemDto responseItemDto = new ResponseItemDto(item.getId(), item.getName(), item.getDescription(), item.getItemStatus());

        if(item.getName() != null && item.getItemGroup() != null){
            responseItemDto.setAssignedToItemGroupId(item.getItemGroup().getId());
            responseItemDto.setAssignedToItemGroupName(item.getItemGroup().getItemGroupName());
        }
        if(item.getItemStatus().equals(ItemStatus.DONE)) {
            long difInMilliseconds = item.getEndDate().getTime() - item.getStartDate().getTime();
            long difMinutes = TimeUnit.MINUTES.convert(difInMilliseconds, TimeUnit.MILLISECONDS);
            responseItemDto.setDuration(String.format("Task took %d minute(s).",difMinutes));
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
