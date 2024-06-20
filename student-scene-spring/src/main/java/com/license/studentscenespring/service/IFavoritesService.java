package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.dto.ItemDTO;

import java.util.List;

public interface IFavoritesService {

    List<EventDTO> getFavoriteEvents(String token);

    List<ItemDTO> getFavoriteItems(String token);

    void addFavoriteEvent(Long id, String token) throws Exception;

    void addFavoriteItem(Long id, String token) throws Exception;

}
