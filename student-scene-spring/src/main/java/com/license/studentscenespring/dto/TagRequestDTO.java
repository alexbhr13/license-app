package com.license.studentscenespring.dto;

import com.license.studentscenespring.util.TagType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TagRequestDTO {
    private String name;
    private TagType tagType;
}
