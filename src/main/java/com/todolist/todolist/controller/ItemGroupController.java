package com.todolist.todolist.controller;

import com.todolist.todolist.controller.dtos.RequestItemGroupDto;
import com.todolist.todolist.controller.dtos.RequestUtils;
import com.todolist.todolist.controller.dtos.ResponseItemGroupDto;
import com.todolist.todolist.controller.dtos.ResponseUtils;
import com.todolist.todolist.model.ItemGroup;
import com.todolist.todolist.services.ItemGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/itemGroups")
public class ItemGroupController {
    private ItemGroupService itemGroupService;

    public ItemGroupController (ItemGroupService itemGroupService){
        this.itemGroupService =itemGroupService;
    }
    /**
     * Input :
     * {
     *     "itemGroupName" : "val",\
     *     "requestItemDtoList" : [
     *          "name"
     *          "description"
     *          "status
     *     ]
     * }
     *
     * Output :
     * {
     *     "id"
     *     "itemGroupName"
     *     "itemList": [
     *         "id",
     *         "name",
     *         "description",
     *         "status",
     *         "itemGroup" : {
     *             "id":
     *             "itemGroupName":
     *             "itemList" : [
     *
     *             ]
     *         }
     *     ]
     * }
     * @param requestItemGroupDto
     * @return
     */

    @PostMapping
    public ResponseItemGroupDto save (@RequestParam RequestItemGroupDto requestItemGroupDto){
        ItemGroup itemGroup = itemGroupService.save(RequestUtils.mapItemGroupToItemGroup(requestItemGroupDto));
        return mapItemGroupToResponseItemGroupDto(itemGroup);
    }

    @GetMapping
    public List<ResponseItemGroupDto> findAll(){
        return mapItemGroupListToResponseItemGroupDtoList(itemGroupService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseItemGroupDto findById(@PathVariable("id") Long id) {
        return mapItemGroupToResponseItemGroupDto(itemGroupService.findById(id));
    }

    @DeleteMapping(path ="/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        itemGroupService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseItemGroupDto mapItemGroupToResponseItemGroupDto(ItemGroup itemGroup) {
        ResponseItemGroupDto responseItemGroupDto = new ResponseItemGroupDto();
        responseItemGroupDto.setItemGroupName(itemGroup.getItemGroupName());
        responseItemGroupDto.setId(itemGroup.getId());
        responseItemGroupDto.setResponseItemDtos(ResponseUtils.mapItemToResponseItemDtoList(itemGroup.getItemList()));
        return responseItemGroupDto;
    }

    //{map}{fromType}{To}{type}
    private List<ResponseItemGroupDto> mapItemGroupListToResponseItemGroupDtoList(List<ItemGroup> itemGroups) {
        List<ResponseItemGroupDto> responseItemGroupDtoList = new ArrayList<>();
        itemGroups
                .forEach(itemGroup -> {
                    ResponseItemGroupDto responseItemGroupDto = mapItemGroupToResponseItemGroupDto(itemGroup);
                    responseItemGroupDtoList.add(responseItemGroupDto);
                });
        return responseItemGroupDtoList;
    }
}
