package com.license.studentscenespring.service;

import com.license.studentscenespring.dto.ItemDTO;
import com.license.studentscenespring.model.Item;
import com.license.studentscenespring.repository.ItemRepository;
import com.license.studentscenespring.security.JWTService;
import com.license.studentscenespring.util.ItemSize;
import com.license.studentscenespring.util.QueryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService{

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final JWTService jwtService;


    public ItemDTO getItemByID(Long id) throws Exception {
        return itemRepository.findById(id).map(itemMapper::toDTO)
                .orElseThrow(()-> new Exception("Item not found"));
    }


    public List<ItemDTO> getItems(
            String tags, String itemSize, String searchInput,
            Boolean myItem, Integer offset, Integer pageSize,
            String token
    ) {
        Specification<Item> specification = Specification.where(null);

        if(tags != null && !tags.isEmpty()) {
            List<String> tagsList = QueryParser.parse(tags);
            specification = specification.and(ItemSpecifications.hasTags(tagsList));
        }

        if(itemSize != null && !itemSize.isEmpty()) {
            List<ItemSize> itemSizeList = QueryParser.parse(itemSize).stream()
                    .map(ItemSize::valueOf)
                    .toList();

            specification = specification.and(ItemSpecifications.hasItemSizes(itemSizeList));
        }

        if(searchInput != null && !searchInput.isEmpty()) {
            specification = specification.and(ItemSpecifications.containsText(searchInput));
        }

        if(myItem != null && myItem) {
            String tokenWithoutBearer = token.substring(7);

            if(jwtService.extractIsAdmin(tokenWithoutBearer)) {
                String userEmail = jwtService.extractEmail(tokenWithoutBearer);
                specification = specification.and(ItemSpecifications.isMyItem(userEmail));
            }
        }

        PageRequest pageRequest = PageRequest.of(offset/pageSize, pageSize);
        Page<Item> itemPage = itemRepository.findAll(specification,pageRequest);

        return itemPage.getContent()
                .stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
