package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.EventDTO;
import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.model.Event;
import com.license.studentscenespring.model.Favorites;
import com.license.studentscenespring.model.Item;
import com.license.studentscenespring.model.User;
import com.license.studentscenespring.repository.EventRepository;
import com.license.studentscenespring.repository.FavoritesRepository;
import com.license.studentscenespring.repository.ItemRepository;
import com.license.studentscenespring.repository.UserRepository;
import com.license.studentscenespring.security.JWTService;
import com.license.studentscenespring.util.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesService implements IFavoritesService{

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ItemRepository itemRepository;
    private final EventMapper eventMapper;
    private final ItemMapper itemMapper;

    public List<EventDTO> getFavoriteEvents(String token) {
        User user = userRepository.
                findByEmail(jwtService.extractEmail(token));
        List<EventDTO> eventDTOS = new ArrayList<>();
        for(Event event: user.getFavorites().getFavoriteEventsId()) {
            eventDTOS.add(eventMapper.toDTO(event));
        }
        return eventDTOS;
    }

    public List<ItemDTO> getFavoriteItems(String token) {
        User user = userRepository.
                findByEmail(jwtService.extractEmail(token));
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for(Item item: user.getFavorites().getFavoriteItemId()) {
            itemDTOS.add(itemMapper.toDTO(item));
        }
        return itemDTOS;
    }


    public void addFavoriteEvent(Long id, String token) throws Exception {
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        Event event = eventRepository.findEventById(id);
        for(Event e: user.getFavorites().getFavoriteEventsId()){
            if(e.equals(event)) throw new Exception("event already marked as favorite");
        }
        user.getFavorites().getFavoriteEventsId().add(event);
        userRepository.saveAndFlush(user);
    }

    public void addFavoriteItem(Long id, String token) throws Exception {
        User user = userRepository.findByEmail(jwtService.extractEmail(token));
        Item item = itemRepository.findItemById(id);
        for(Item i: user.getFavorites().getFavoriteItemId()){
            if(i.equals(item)) throw new Exception("item already marked as favorite");
        }
        user.getFavorites().getFavoriteItemId().add(item);
        userRepository.saveAndFlush(user);
    }
}
