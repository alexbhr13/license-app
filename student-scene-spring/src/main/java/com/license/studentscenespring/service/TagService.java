package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.TagRequestDTO;
import com.license.studentscenespring.model.Tag;
import com.license.studentscenespring.repository.TagRepository;
import com.license.studentscenespring.util.TagType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService{

    private final TagRepository tagRepository;

    public List<TagRequestDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return convertTagsToDTOs(tags);
    }

    public List<TagRequestDTO> getAllSpecificTags(String tagType) {
        TagType type = TagType.valueOf(tagType);
        List<Tag> tags = tagRepository.findAllByTagType(type);
        return convertTagsToDTOs(tags);
    }

    private TagRequestDTO convertTagToDTO(Tag tag) {
        return TagRequestDTO.builder().name(tag.getName()).tagType(tag.getTagType()).build();
    }

    private List<TagRequestDTO> convertTagsToDTOs(List<Tag> tags) {
        return tags.stream()
                .map(tag -> new TagRequestDTO(tag.getName(), tag.getTagType()))
                .collect(Collectors.toList());
    }


}
