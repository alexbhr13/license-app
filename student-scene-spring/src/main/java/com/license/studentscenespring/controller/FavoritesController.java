package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.service.FavoritesService;
import com.license.studentscenespring.service.IFavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/v1/favorites")
@RestController
@RequiredArgsConstructor
public class FavoritesController {

    private final IFavoritesService favoritesService;

    @GetMapping("/events")
    public ResponseEntity<?> getFavoriteEvents(@RequestHeader ("Authorization") String token){
        try{
            List<EventDTO> favoriteEvents = favoritesService.getFavoriteEvents(token);
            return new ResponseEntity<>(favoriteEvents, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/items")
    public ResponseEntity<?> getFavoriteItems(@RequestHeader ("Authorization") String token){
        try{
            List<ItemDTO> favoriteEvents = favoritesService.getFavoriteItems(token);
            return new ResponseEntity<>(favoriteEvents, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addFavoriteEvent(
            @RequestParam ("id") Long id,
            @RequestHeader ("Authorization") String token
    ){
        try{
            favoritesService.addFavoriteEvent(id,token);
            return ResponseEntity.status(HttpStatus.OK).body("event marked as favorite");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addFavoriteItem(
            @RequestParam ("id") Long id,
            @RequestHeader ("Authorization") String token
    ){
        try{
            favoritesService.addFavoriteItem(id,token);
            return ResponseEntity.status(HttpStatus.OK).body("item marked as favorite");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
