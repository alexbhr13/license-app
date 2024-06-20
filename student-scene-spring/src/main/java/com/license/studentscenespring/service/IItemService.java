package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.ItemDTO;

import java.util.List;

public interface IItemService {

    ItemDTO getItemByID(Long id) throws Exception;

    List<ItemDTO> getItems(
            String tags, String itemSize, String searchInput,
            Boolean myItem, Integer offset, Integer pageSize,
            String token
    );
}
