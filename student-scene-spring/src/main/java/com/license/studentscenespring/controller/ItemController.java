package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.model.Item;
import com.license.studentscenespring.service.IItemService;
import com.license.studentscenespring.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/v1/items")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final IItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemByID(@PathVariable Long id) {
        try {
            ItemDTO itemDTO = itemService.getItemByID(id);
            return new ResponseEntity<>(itemDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<ItemDTO>> getItems(
            @RequestParam(required = false, value = "tags") String tags,
            @RequestParam(required = false, value = "itemSize") String itemSize,
            @RequestParam(required = false, value = "search_input") String search_input,
            @RequestParam(required = false, value = "my_events") Boolean my_item,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "page_size") Integer page_size,
            @RequestHeader("Authorization") String token
    ) {
        try {
            List<ItemDTO> items = itemService.getItems(tags, itemSize,search_input,
                    my_item, offset, page_size, token);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
