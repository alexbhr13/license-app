package com.license.studentscenespring.controller;

import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestParam ("itemId") Long itemId,
            @RequestHeader ("Authorization") String token) {
        try{
            cartService.addItemToCart(itemId,token);
            return ResponseEntity.status(HttpStatus.OK).body("item added to cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromCart(
            @RequestParam ("itemId") Long itemId,
            @RequestHeader ("Authorization") String token) {
        try{
            cartService.removeItemFromCart(itemId,token);
            return ResponseEntity.status(HttpStatus.OK).body("item removed from the cart");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCart(@RequestHeader ("Authorization") String token) {
        try{
            List<ItemDTO> itemDTOS =  cartService.getCartItems(token);
            return new ResponseEntity<>(itemDTOS,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/empty")
    public ResponseEntity<?> emptyCart(@RequestHeader ("Authorization") String token) {
        try{
            cartService.emptyCart(token);
            return ResponseEntity.status(HttpStatus.OK).body("cart emptied");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
