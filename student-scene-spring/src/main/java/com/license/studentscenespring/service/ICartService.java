package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.ItemDTO;

import java.util.List;

public interface ICartService {
    void addItemToCart(Long itemId, String token);

    void removeItemFromCart(Long itemId, String token);

    List<ItemDTO> getCartItems(String token);

    void emptyCart(String token);
}
