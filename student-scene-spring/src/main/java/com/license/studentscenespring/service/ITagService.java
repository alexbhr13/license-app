package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.TagRequestDTO;

import java.util.List;

public interface ITagService {
    List<TagRequestDTO> getAllTags();

    List<TagRequestDTO> getAllSpecificTags(String tagType);
}
