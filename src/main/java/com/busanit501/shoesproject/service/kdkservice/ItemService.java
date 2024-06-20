package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();

    Optional<Item> getItemById(Long id);

    void saveItem(Item item);

    void deleteItem(Long id);
}