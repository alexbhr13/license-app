package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    public ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .photo(item.getPhoto())
                .description(item.getDescription())
                .price(item.getPrice())
                .tagName(item.getTagName())
                .itemSize(item.getItemSize())
                .build();
    }

    public Item fromDTO(ItemDTO itemDTO) {
        return Item.builder()
                .id(itemDTO.getId())
                .name(itemDTO.getName())
                .photo(itemDTO.getPhoto())
                .description(itemDTO.getDescription())
                .price(itemDTO.getPrice())
                .tagName(itemDTO.getTagName())
                .itemSize(itemDTO.getItemSize())
                .build();
    }


}
