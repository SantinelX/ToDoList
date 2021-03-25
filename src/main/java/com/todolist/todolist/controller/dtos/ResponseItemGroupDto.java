package com.todolist.todolist.controller.dtos;

import java.util.List;

public class ResponseItemGroupDto {

    private Long id;
    private String itemGroupName;
    private List<ResponseItemDto>responseItemDtos;

    public ResponseItemGroupDto(){

    }

    public ResponseItemGroupDto(Long id, List<ResponseItemDto> responseItemDtos) {
        this.id = id;
        this.responseItemDtos = responseItemDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ResponseItemDto> getResponseItemDtos() {
        return responseItemDtos;
    }

    public void setResponseItemDtos(List<ResponseItemDto> responseItemDtos) {
        this.responseItemDtos = responseItemDtos;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }
}
