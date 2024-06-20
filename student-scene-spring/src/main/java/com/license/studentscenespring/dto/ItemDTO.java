package com.license.studentscenespring.dto;

import com.license.studentscenespring.util.ItemSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String photo;
    private String name;
    private String description;
    private float price;
    private String tagName;
    private ItemSize itemSize;
}
