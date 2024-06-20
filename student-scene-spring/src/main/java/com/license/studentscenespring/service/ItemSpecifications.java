package com.license.studentscenespring.service;

import com.license.studentscenespring.model.Item;
import com.license.studentscenespring.util.ItemSize;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ItemSpecifications {

    public static Specification<Item> hasTags(List<String> tags) {
        return (root, query, criteriaBuilder) ->
                root.get("tagName").in(tags);
    }

    public static Specification<Item> containsText(String searchInput) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + searchInput + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + searchInput + "%")
                );
    }

    public static Specification<Item> isMyItem(String userEmail) {
        return (root, query, criteriaBuilder) -> (
                criteriaBuilder.equal(root.get("adminEmail"), userEmail)
        );

    }

    public static Specification<Item> hasItemSizes(List<ItemSize> itemSizes) {
        return (root, query, criteriaBuilder) ->
                root.get("itemSize").in(itemSizes);
    }
}
