package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.TagRequestDTO;
import com.license.studentscenespring.service.ITagService;
import com.license.studentscenespring.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final ITagService tagService;

    @GetMapping()
    @Cacheable(value = "tags")
    public ResponseEntity<List<TagRequestDTO>> getAllTags() {
        List<TagRequestDTO> tagDTOs = tagService.getAllTags();
        return new ResponseEntity<>(tagDTOs, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TagRequestDTO>> getSpecificTags(@RequestParam ("tagType") String tagType) {
        List<TagRequestDTO> tagDtos = tagService.getAllSpecificTags(tagType);
        return new ResponseEntity<>(tagDtos, HttpStatus.OK);
    }

}
