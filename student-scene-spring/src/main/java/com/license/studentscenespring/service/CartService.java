package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.model.Item;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.ItemRepository;
import com.license.studentscenespring.repository.UserRepository;
import com.license.studentscenespring.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ItemMapper itemMapper;


    @Override
    public void addItemToCart(Long itemId, String token) {
        Item item = itemRepository.findItemById(itemId);
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        user.getCart().getItems().add(item);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void removeItemFromCart(Long itemId, String token) {
        Item item = itemRepository.findItemById(itemId);
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        user.getCart().getItems().remove(item);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<ItemDTO> getCartItems(String token) {
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item: user.getCart().getItems()) { itemDTOS.add(itemMapper.toDTO(item)); }
        return itemDTOS;
    }

    @Override
    public void emptyCart(String token) {
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        user.getCart().getItems().clear();
        userRepository.saveAndFlush(user);
    }


}
